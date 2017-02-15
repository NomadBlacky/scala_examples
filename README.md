# Table Of Contents


## ClassSpec

+ [require ... 引数を検証する](src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L10)
+ [unapplyを使用したパターンマッチ](src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L23)
+ [caseクラスとパターンマッチ](src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L49)
+ [抽象クラス](src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L67)
+ [メソッドをvalでオーバーライドする](src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L84)
+ [sealedによる継承制限とパターンマッチ](src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L99)

## ExceptionSpec

+ [基本的なtry-catch-finally](src/test/scala/org/nomadblacky/scala/samples/exceptions/ExceptionSpec.scala#L19)

## ForSpec

+ [コレクションのイテレート](src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L17)
+ [要素のフィルタ](src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L23)
+ [yield ... forの結果を新しいコレクションとして返す](src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L31)
+ [[Sample] 2つのコレクションを同じ順序で取り出して処理する](src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L36)

## FunctionSpec

+ [関数リテラル](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L7)
+ [Functionトレイト](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L12)
+ [関数を引数に取る関数](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L17)
+ [プレースホルダ構文](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L23)
+ [プレースホルダ構文2](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L36)
+ [関数の部分適用](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L42)
+ [関数のカリー化](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L53)
+ [関数のカリー化2](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L64)
+ [関数の引数を遅延評価する](src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L71)

## FunctionalProgrammingInScala

+ [[EXERCISE 2.1] フィボナッチ数](src/test/scala/org/nomadblacky/scala/samples/functional/FunctionalProgrammingInScala.scala#L13)
+ [[EXERCISE 2.1] フィボナッチ数(Stream)](src/test/scala/org/nomadblacky/scala/samples/functional/FunctionalProgrammingInScala.scala#L28)
+ [[EXERCISE 2.2] isSortedの実装](src/test/scala/org/nomadblacky/scala/samples/functional/FunctionalProgrammingInScala.scala#L36)
+ [[EXERCISE 2.3] カリー化](src/test/scala/org/nomadblacky/scala/samples/functional/FunctionalProgrammingInScala.scala#L53)
+ [[EXERCISE 2.4] 逆カリー化](src/test/scala/org/nomadblacky/scala/samples/functional/FunctionalProgrammingInScala.scala#L67)
+ [[EXERCISE 2.5] 関数の合成](src/test/scala/org/nomadblacky/scala/samples/functional/FunctionalProgrammingInScala.scala#L81)

## IterableSpec

+ [grouped ... 指定サイズのListにまとめたIteratorを返す](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L10)
+ [sliding ... ウィンドウをずらしながら参照するIteratorを返す](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L22)
+ [takeRight ... コレクションの最後のn個の要素を取り出す](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L35)
+ [dropRight ... コレクションの最後のn個の要素を取り除く](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L39)
+ [zip ... 2つのコレクションから対応する要素をペアにする](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L43)
+ [zipAll ... 2つのコレクションから対応する要素をペアにする](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L49)
+ [zipWithIndex ... コレクションの要素と添字をペアにしたIterableを返す](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L55)
+ [sameElements ... 2つのコレクションが同じ要素を同じ順序で格納しているかを返す](src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L59)

## LazySpec

+ [lazy ... 変数を遅延評価する](src/test/scala/org/nomadblacky/scala/samples/scala/LazySpec.scala#L7)

## ListSpec

+ [List() ... Listを生成する](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L15)
+ [() ... Listから値を取り出す](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L19)
+ [Nil ... 空のListを作成する](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L26)
+ [ListBuffer ... ミュータブルなList](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L31)
+ [:: :+ ... Listに値を追加する](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L40)
+ [::: ... List同士を連結する](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L48)
+ [withFilter ... 中間データを作らない](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L54)
+ [view ... none-strict(中間データを作らない)なコレクションに変換する](src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L64)

## MapSpec

+ [Map() ... Mapを生成する](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L8)
+ [Map.empty ... 空のMapを生成する](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L12)
+ [() ... Mapから値を取り出す](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L17)
+ [+ ... 指定したキーと値のペアを追加したMapを返す](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L24)
+ [- ... 指定したキーの要素を抜いたMapを返す](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L29)
+ [ミュータブルなMap](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L34)
+ [keySet ... キーのSetを返す](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L40)
+ [values ... 値をIterableで返す](src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L45)

## MatchSpec

+ [基本的なマッチング](src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L16)
+ [型のマッチング](src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L25)
+ [パターンガード](src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L34)
+ [リストのマッチング](src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L42)

## OptionSpec

+ [Optionの基本](src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L10)
+ [Optionから値を取り出す](src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L24)
+ [Optionに値が含まれる場合のみに実行させる](src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L43)

## ScoptSpec

+ [コマンドライン引数を解析する](src/test/scala/org/nomadblacky/scala/samples/libraries/scopt/ScoptSpec.scala#L38)
+ [引数から日付を取得](src/test/scala/org/nomadblacky/scala/samples/libraries/scopt/ScoptSpec.scala#L47)
+ [引数からファイルを取得](src/test/scala/org/nomadblacky/scala/samples/libraries/scopt/ScoptSpec.scala#L57)

## TraitSpec

+ [トレイトの基本](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L15)
+ [複数のトレイトをミックスインする](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L26)
+ [インスタンス化のタイミングでミックスインする](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L42)
+ [同じシグネチャのメソッドを複数ミックスインした場合](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L53)
+ [superで呼び出すトレイトのメソッドを指定する](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L67)
+ [トレイトを単体で利用する](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L87)
+ [abstract override で既存のメソッドに新しい処理を追加する](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L106)
+ [トレイトの指定順序](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L130)
+ [自分型アノテーション](src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L161)

## TraversableSpec

+ [++ ... Traversableを連結する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L14)
+ [head / headOptional ... 先頭の要素を取得する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L18)
+ [last / lastOption ... 最後の要素を取得する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L26)
+ [init ... 最後以外の要素を取得する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L34)
+ [tail ... 最初以外の要素を取得する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L41)
+ [foldLeft ... 要素の先頭から畳み込みを行う](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L48)
+ [foldRight ... 要素の最後から畳み込みを行う](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L60)
+ [reduceLeft ... 最初の要素を初期値として畳み込みを行う](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L73)
+ [reduceRight ... 最後の要素を初期値として畳み込みを行う](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L82)
+ [foreach ... 戻り値なしで全ての要素を処理する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L92)
+ [filter ... 条件に一致する要素のみを抜き出す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L100)
+ [filter ... 条件に一致しない要素のみを抜き出す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L105)
+ [drop ... 指定した数の要素を先頭から取り除く](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L110)
+ [dropWhile ... 条件がfalseになるまで要素を取り除く](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L118)
+ [take ... 指定した数の要素を先頭から取り出す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L123)
+ [takeWhile ... 条件がfalseになるまで要素を取り出す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L131)
+ [map ... 要素に関数を適用して新しいコレクションを返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L136)
+ [flatMap ... 要素に関数を適用して新しいコレクションを返しflattenする](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L140)
+ [flatten ... 入れ子になったコレクションを1次元にする](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L144)
+ [splitAt ... コレクションを分割する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L148)
+ [slice ... コレクションの一部を抜き出す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L152)
+ [partition ... 条件を満たす要素とそうでない要素に分割する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L156)
+ [span ... 条件がfalseとなった要素を堺にコレクションを分割する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L161)
+ [groupBy ... 関数の結果をキーとして要素をMapにして返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L166)
+ [unzip ... 要素を2つのコレクションに分割する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L170)
+ [unzip3 ... 要素を3つのコレクションに分割する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L175)
+ [find ... 最初に条件を満たした要素をOptionで返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L179)
+ [exists ... 条件を満たす要素があるか判定する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L185)
+ [forall ... 全ての要素が条件を満たすか判定する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L190)
+ [count ... 条件を満たす要素の個数を返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L195)
+ [size/length ... 要素の個数を返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L200)
+ [min ... 最小値を返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L205)
+ [max ... 最大値を返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L210)
+ [sum ... 合計値を返す](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L215)
+ [mkString ... コレクションから文字列を作る](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L221)
+ [toArray ... コレクションをArrayに変換する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L227)
+ [toBuffer ... コレクションをBufferに変換する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L233)
+ [toIndexedSeq ... コレクションをIndexedSeqに変換する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L237)
+ [toList ... コレクションをListに変換する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L241)
+ [toStream ... コレクションをStreamに変換する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L245)
+ [par ... 並列コレクション(ParIterable)に変換する](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L249)
+ [view/force ... コレクションの操作を遅延評価させる(中間データを作らない)](src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L253)

## TupleSpec

+ [タプルを生成する](src/test/scala/org/nomadblacky/scala/samples/collections/TupleSpec.scala#L7)
+ [タプルから値を取り出す](src/test/scala/org/nomadblacky/scala/samples/collections/TupleSpec.scala#L11)
+ [タプルの要素に意味付けをする](src/test/scala/org/nomadblacky/scala/samples/collections/TupleSpec.scala#L18)

## TypeParameterSpec

+ [Scalaにおける型の検査](src/test/scala/org/nomadblacky/scala/samples/scala/TypeParameterSpec.scala#L10)

## XmlSpec

+ [xmlリテラル](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L10)
+ [値を埋め込む](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L16)
+ [ファイルから読み込む](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L22)
+ [要素を取得する1](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L28)
+ [要素を取得する2](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L34)
+ [要素を取得する3](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L41)
+ [属性から要素を取得する](src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L48)
