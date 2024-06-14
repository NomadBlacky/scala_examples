package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter06

import org.scalactic.{Equality, TolerantNumerics}
import org.scalatest.{FunSpec, Matchers}

class Chapter06Spec extends FunSpec with Matchers {

  implicit val doubleEquality: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(0.0001)

  /** [EXERCISE 6.1]
    */
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (n, rng2) = rng.nextInt
    val n2        = if (n == Int.MinValue) Int.MaxValue else Math.abs(n)
    (n2, rng2)
  }

  /** [EXERCISE 6.2]
    */
  def double(rng: RNG): (Double, RNG) = {
    val (n, rng2) = nonNegativeInt(rng)
    (n / (Int.MaxValue.toDouble + 1), rng2)
  }

  it("[EXERCISE 6.1] ランダムな 0~Int.MaxValue のIntを生成する関数") {
    nonNegativeInt(SimpleRNG(10)) shouldBe (3847489, SimpleRNG(252149039181L))
  }

  it("[EXERCISE 6.2] ランダムな 0~1未満 のDoubleを生成する関数") {
    double(SimpleRNG(10)) shouldBe (0.0017916266806423664, SimpleRNG(252149039181L))
  }

  it("[EXERCISE 6.3] ランダムな値のタプルを生成する関数") {
    def intDouble(rng: RNG): ((Int, Double), RNG) = {
      val (i, rng2) = nonNegativeInt(rng)
      val (d, rng3) = double(rng2)
      ((i, d), rng3)
    }
    def doubleInt(rng: RNG): ((Double, Int), RNG) = {
      val (d, rng2) = double(rng)
      val (i, rng3) = nonNegativeInt(rng2)
      ((d, i), rng3)
    }
    def double3(rng: RNG): ((Double, Double, Double), RNG) = {
      val (d1, rng2) = double(rng)
      val (d2, rng3) = double(rng2)
      val (d3, rng4) = double(rng3)
      ((d1, d2, d3), rng4)
    }

    intDouble(SimpleRNG(10)) shouldBe ((3847489, 0.6213264381513), SimpleRNG(87443922374356L))
    doubleInt(SimpleRNG(10)) shouldBe ((0.0017916266806423664, 1334288366), SimpleRNG(87443922374356L))
    double3(SimpleRNG(10)) shouldBe (
      (0.0017916266806423664, 0.6213264381513, 0.6923740776255727),
      SimpleRNG(
        97442988689487L
      )
    )
  }

  it("[EXERCISE 6.4] ランダムな整数のリストを作成する関数") {
    def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
      (1 to count).foldLeft((List.empty[Int], rng)) { case ((list, r), _) =>
        val (i, r2) = nonNegativeInt(r)
        (list :+ i, r2)
      }
    }

    val expect = (List(3847489, 1334288366, 1486862010, 711662464, 1453296530), SimpleRNG(186231735346465L))

    ints(5)(SimpleRNG(10)) shouldBe expect
  }

  // 6.4 状態の処理に適したAPI
  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] = rng => (a, rng)

  /** 状態そのものを変化させずに状態アクションの出力を変換するmap関数 関数合成のようなもの
    */
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

  def nonNegativeEven: Rand[Int] = map(nonNegativeInt)(i => i - i % 2)

  val double2: Rand[Double] = map(nonNegativeInt)(d => d / (Int.MaxValue.toDouble + 1))

  it("[EXERCISE 6.5] double関数の再実装") {
    double2(SimpleRNG(10)) shouldBe (0.0017916266806423664, SimpleRNG(252149039181L))
  }

  // 6.4.1 状態アクションの結合

  /** [EXERCISE 6.6]
    */
  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (a, rng2) = ra(rng)
    val (b, rng3) = rb(rng2)
    (f(a, b), rng3)
  }

  it("[EXERCISE 6.6] mapの再実装") {
    val func: Rand[(Int, Double)] = map2(nonNegativeEven, double2)((_, _))
    func(SimpleRNG(10)) shouldBe ((3847488, 0.6213264381513), SimpleRNG(87443922374356L))
  }

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] = map2(ra, rb)((_, _))

  val randIntDouble: Rand[(Int, Double)] = both(int, double)
  val randDoubleInt: Rand[(Double, Int)] = both(double, int)

  it("[EXERCISE 6.7] sequenceの実装") {
    def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = {
      val init: Rand[List[A]] = rng => (List.empty[A], rng)
      fs.foldLeft(init) { (rand, f) => rng =>
        {
          val (a, rng2) = rand(rng)
          val (b, rng3) = f(rng2)
          (a :+ b, rng3)
        }
      }
    }
    def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
      sequence(List.fill(count)(nonNegativeInt(_)))(rng)
    }
    val expect = (List(3847489, 1334288366, 1486862010, 711662464, 1453296530), SimpleRNG(186231735346465L))

    ints(5)(SimpleRNG(10)) shouldBe expect

    // https://github.com/fpinscala/fpinscala/blob/master/answerkey/state/07.answer.scala
    def sequence2[A](fs: List[Rand[A]]): Rand[List[A]] =
      fs.foldRight(unit(List.empty[A]))((f, acc) => map2(f, acc)(_ :: _))

    def ints2(count: Int): Rand[List[Int]] =
      sequence(List.fill(count)(nonNegativeInt))

    ints2(5)(SimpleRNG(10)) shouldBe expect
  }

  // 6.4.2 入れ子の状態アクション
  def nonNegativeLessThen(n: Int): Rand[Int] = { rng =>
    val (i, rng2) = nonNegativeInt(rng)
    val mod       = i % n
    if (i + (n - 1) - mod >= 0)
      (mod, rng2)
    else
      nonNegativeLessThen(n)(rng)
  }

  /** [EXERCISE 6.8]
    */
  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
    rng => {
      val (v, rng2) = f(rng)
      g(v)(rng2)
    }

  it("[EXERCISE 6.8] flatMapの実装") {
    def nonNegativeLessThen(n: Int): Rand[Int] =
      flatMap(nonNegativeInt) { i =>
        val mod = i % n
        if (i + (n - 1) - mod >= 0)
          unit(mod)
        else
          nonNegativeLessThen(n)
      }

    nonNegativeLessThen(20)(SimpleRNG(10)) shouldBe (9, SimpleRNG(252149039181L))
  }

  it("[EXERCISE 6.9] map,map2の再実装") {
    def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
      flatMap(s)(a => unit(f(a)))

    def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
      flatMap(ra)(a => map(rb)(b => f(a, b)))

    map(int)(_.toString)(SimpleRNG(10)) shouldBe ("3847489", SimpleRNG(252149039181L))
    map2(int, double)((i, d) => s"$i:$d")(SimpleRNG(10)) shouldBe (
      "3847489:0.6213264381513",
      SimpleRNG(
        87443922374356L
      )
    )
  }

  describe("6.5 状態アクションデータ型の一般化") {

    /** より汎用的なmap関数
      */
    def map_[S, A, B](a: S => (A, S))(f: A => B): S => (B, S) =
      s => {
        val (a2, s2) = a(s)
        (f(a2), s2)
      }

    /** Randより汎用的な型 何らかの状態を伴う計算、状態アクションなどの省略形
      */
    // type State[S, +A] = S => (A, S)

    /** クラスとして独立させ、関数を追加する。 この型を使い、ステートフルなプログラムの共通パターンを表現する関数を記述すればよい。
      *
      * [EXERCISE 6.10]
      */
    case class State[S, +A](run: S => (A, S)) {
      def map[B](f: A => B): State[S, B] =
        flatMap(a => State.unit(f(a)))

      def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
        flatMap(a => sb.map(b => f(a, b)))

      def flatMap[B](f: A => State[S, B]): State[S, B] =
        State { (s: S) =>
          val (a, s2) = run(s)
          f(a).run(s2)
        }
    }

    /** [EXERCISE 6.10]
      */
    object State {
      def unit[S, A](a: A): State[S, A] =
        State(s => (a, s))

      def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] =
        sas.foldRight(unit[S, List[A]](List.empty))((st, acc) => st.map2(acc)(_ :: _))
    }

    /** RandをStateのエイリアスとする
      */
    type Rand[A] = State[RNG, A]

    it("[EXERCISE 6.10] 関数の一般化") {
      State.unit[RNG, Int](1).run(SimpleRNG(10)) shouldBe (1, SimpleRNG(10))

      val rand: Rand[Int] = State(int)
      rand.map(i => i.toString).run(SimpleRNG(10)) shouldBe ("3847489", SimpleRNG(252149039181L))
      rand.map2(State(double))((i, d) => s"$i:$d").run(SimpleRNG(10)) shouldBe (
        "3847489:0.6213264381513",
        SimpleRNG(
          87443922374356L
        )
      )
      rand.flatMap(i => State.unit(i.toString)).run(SimpleRNG(10)) shouldBe ("3847489", SimpleRNG(252149039181L))
    }
  }

  describe("6.6 純粋関数型の命令型プログラミング") {
    type Rand[A] = State[RNG, A]
    val int: Rand[Int] = State((rng: RNG) => rng.nextInt)
    def ints(count: Int): Rand[List[Int]] =
      State.sequence(List.fill(count)(int))

    // 関数型の表現だと少しわかりにくい
    val ns: Rand[List[Int]] =
      int.flatMap(x => int.flatMap(y => ints(x).map(xs => xs.map(_ % y))))

    // for内包表記を使うと命令形のスタイルで書ける
    val ns2: Rand[List[Int]] = for {
      x  <- int
      y  <- int
      xs <- ints(x)
    } yield xs.map(_ % y)

    // 任意の方法で状態を変更する
    def modify[S](f: S => S): State[S, Unit] =
      for {
        s <- get
        _ <- set(f(s))
      } yield ()

    def get[S]: State[S, S]          = State(s => (s, s))
    def set[S](s: S): State[S, Unit] = State(_ => ((), s))

    it("[EXERCISE 6.11] 有限状態オートマトンの実装") {

      /**   - ロックされた状態の自動販売機に硬貨を投入すると、スナックが残っている場合はロックが解除される。
        *   - ロックが解除された状態の自動販売機のハンドルを回すと、スナックが出てきてロックがかかる。
        *   - ロックされた状態でハンドルを回したり、ロックが解除された状態で硬貨を投入したりしても何も起こらない
        *   - スナックが売り切れた自動販売機は入力を全て無視する。
        */
      sealed trait Input
      case object Coin extends Input
      case object Turn extends Input
      case class Machine(locked: Boolean, candies: Int, coins: Int)

      // https://github.com/fpinscala/fpinscala/blob/master/answerkey/state/11.answer.scala
      object Candy {
        def update: Input => Machine => Machine =
          i =>
            m =>
              (i, m) match {
                case (_, Machine(_, 0, _))        => m
                case (Coin, Machine(false, _, _)) => m
                case (Turn, Machine(true, _, _))  => m
                case (Coin, Machine(true, candy, coin)) =>
                  Machine(false, candy, coin + 1)
                case (Turn, Machine(false, candy, coin)) =>
                  Machine(true, candy - 1, coin)
              }

        def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] =
          for {
            // State#sequence(List[State[Machine, Unit]]): State[Machine, List[Unit]]
            _ <- State.sequence(
              // List[Input]#map(Input => State[Machine, Unit]): List[State[Machine, Unit]]
              inputs map (modify[Machine] _ compose update)
            )
            // State[Machine, Machine]
            s <- get
          } yield (s.coins, s.candies)

        // 要約
        def simulateMachine2(inputs: List[Input]): State[Machine, (Int, Int)] = {
          State
            .sequence(inputs map (modify[Machine] _ compose update))
            .flatMap((_: List[Unit]) => {
              get.map((m: Machine) => (m.coins, m.candies))
            })
        }
      }

      val s1 = Candy.simulateMachine(
        // 4回正しい購入をする
        List(Coin, Turn, Coin, Turn, Coin, Turn, Coin, Turn)
      )
      s1.run(Machine(true, 5, 10)) shouldBe ((14, 1), Machine(true, 1, 14))

      val s2 = Candy.simulateMachine(
        List(
          Turn,
          Turn, // 2回空回し
          Coin,
          Coin, // 2回空コイン
          Coin,
          Turn,
          Coin,
          Turn,
          Coin,
          Turn,
          Coin,
          Turn,
          Coin,
          Turn, // 5回購入
          Coin,
          Turn // 何も起こらない
        )
      )
      s2.run(Machine(true, 5, 10)) shouldBe ((15, 0), Machine(true, 0, 15))
    }
  }
}
