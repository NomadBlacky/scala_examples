package dev.nomadblacky.scala_examples.opentelemetry

import io.opentelemetry.api.common.{AttributeKey, Attributes}
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter
import io.opentelemetry.sdk.OpenTelemetrySdk
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.`export`.BatchSpanProcessor
import org.apache.logging.log4j.scala.Logging

import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit
import scala.util.Random

object OpenTelemetryExample extends Logging {
  def main(args: Array[String]): Unit = {
    val tracerProvider = SdkTracerProvider
      .builder()
      .addSpanProcessor(
        BatchSpanProcessor
          .builder(OtlpGrpcSpanExporter.builder().build())
          .setScheduleDelay(100, TimeUnit.MILLISECONDS)
          .build()
      )
      .build()

    val openTelemetry =
      OpenTelemetrySdk
        .builder()
        .setTracerProvider(tracerProvider)
        .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
        .buildAndRegisterGlobal()

    java.util.logging.Logger.getLogger("a").info("foo")

    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run(): Unit = tracerProvider.close()
    })

    val tracer = openTelemetry.getTracer("scala_examples")

    val span = tracer.spanBuilder("span1").startSpan()

    val startAt = OffsetDateTime.now()
    try {
      logger.info("Start a span")
      span.addEvent("Start")
      Thread.sleep(1000 * (Random.nextInt(5) + 1))
    } catch {
      case e: Throwable => span.setStatus(StatusCode.ERROR, e.getMessage)
    } finally {
      logger.info("End the span")
      val endAt = OffsetDateTime.now()
      val attr =
        Attributes.of[java.lang.Long](AttributeKey.longKey("duration"), endAt.toEpochSecond - startAt.toEpochSecond)
      span.addEvent("End", attr)
      span.end()
    }
  }
}
