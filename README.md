# Scala Code Examples

Scala コードのサンプル集です。

## もくじ

### Scala の基本

+ [Scala の基本](basics)
+ [Scala3](scala3)

### ライブラリの使用例

+ [shapeless](shapeless)

---

## 旧もくじ

以下は内容が古い可能性があります。

### Akka HTTP

+ [クライアントAPI](legacy/src/test/scala/org/nomadblacky/scala/samples/akka/http/AkkaHttpSpec.scala#L22)

### Akka Streams

+ [ことはじめ](legacy/src/test/scala/org/nomadblacky/scala/samples/akka/streams/AkkaStreamsSpec.scala#L24)

### Ammonite-Ops

+ [パスを参照する](legacy/src/test/scala/org/nomadblacky/scala/samples/ammonite/AmmoniteSpec.scala#L18)
+ [基本的なファイル操作](legacy/src/test/scala/org/nomadblacky/scala/samples/ammonite/AmmoniteSpec.scala#L32)
+ [ファイルの読み書き](legacy/src/test/scala/org/nomadblacky/scala/samples/ammonite/AmmoniteSpec.scala#L46)
+ [拡張演算子とワンライナー](legacy/src/test/scala/org/nomadblacky/scala/samples/ammonite/AmmoniteSpec.scala#L60)
+ [サブプロセスの実行](legacy/src/test/scala/org/nomadblacky/scala/samples/ammonite/AmmoniteSpec.scala#L82)

### Chapter02Spec

+ [テキストファイルの読み込み](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter02Spec.scala#L15)
+ [10. 行数のカウント](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter02Spec.scala#L20)

### Chapter06Spec

+ [[EXERCISE 6.1] ランダムな 0~Int.MaxValue のIntを生成する関数](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L27)
+ [[EXERCISE 6.2] ランダムな 0~1未満 のDoubleを生成する関数](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L31)
+ [[EXERCISE 6.3] ランダムな値のタプルを生成する関数](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L35)
+ [[EXERCISE 6.4] ランダムな整数のリストを作成する関数](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L60)
+ [[EXERCISE 6.5] double関数の再実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L94)
+ [[EXERCISE 6.6] mapの再実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L109)
+ [[EXERCISE 6.7] sequenceの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L119)
+ [[EXERCISE 6.8] flatMapの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L166)
+ [[EXERCISE 6.9] map,map2の再実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L179)
+ [6.5 状態アクションデータ型の一般化 [EXERCISE 6.10] 関数の一般化](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L245)
+ [6.6 純粋関数型の命令型プログラミング [EXERCISE 6.11] 有限状態オートマトンの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter06/Chapter06Spec.scala#L284)

### EffectiveScalaSpec

+ [関数定義の中で直接パターンマッチを使う](legacy/src/test/scala/org/nomadblacky/scala/samples/best_practice/EffectiveScalaSpec.scala#L15)
+ [型エイリアスを使う](legacy/src/test/scala/org/nomadblacky/scala/samples/best_practice/EffectiveScalaSpec.scala#L38)
+ [コレクション処理の可読性を保つ](legacy/src/test/scala/org/nomadblacky/scala/samples/best_practice/EffectiveScalaSpec.scala#L57)
+ [Javaコレクションとの相互変換](legacy/src/test/scala/org/nomadblacky/scala/samples/best_practice/EffectiveScalaSpec.scala#L92)

### Futureの使い方

+ [Futureの基本](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FutureSpec.scala#L25)
+ [Await ... スレッドの終了を待機する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FutureSpec.scala#L52)
+ [onComplete ... コールバック関数を定義する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FutureSpec.scala#L73)
+ [map ... Futureの計算結果を処理するFutureを取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FutureSpec.scala#L88)
+ [sequence ... Seq[Future] を Future[Seq] に変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FutureSpec.scala#L117)

### Iterable - コレクションの要素をひとつずつ返すトレイト

+ [grouped ... 指定サイズのListにまとめたIteratorを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L12)
+ [sliding ... ウィンドウをずらしながら参照するIteratorを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L24)
+ [takeRight ... コレクションの最後のn個の要素を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L37)
+ [dropRight ... コレクションの最後のn個の要素を取り除く](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L41)
+ [zip ... 2つのコレクションから対応する要素をペアにする](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L45)
+ [zipAll ... 2つのコレクションから対応する要素をペアにする](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L51)
+ [zipWithIndex ... コレクションの要素と添字をペアにしたIterableを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L57)
+ [sameElements ... 2つのコレクションが同じ要素を同じ順序で格納しているかを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/IterableSpec.scala#L61)

### JFreeChart - グラフを描画するJavaライブラリ

+ [Part1 ... 円グラフを作成する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/jfreechart/JFreeChartSpec.scala#L25)
+ [Part2 ... ヒストグラム](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/jfreechart/JFreeChartSpec.scala#L35)

### JVM関連のあれこれ

+ [クラスパスの一覧を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/JVMSpec.scala#L9)

### List - 単方向リスト

+ [List() ... Listを生成する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L17)
+ [() ... Listから値を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L21)
+ [Nil ... 空のListを作成する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L28)
+ [ListBuffer ... ミュータブルなList](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L33)
+ [:: :+ ... Listに値を追加する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L42)
+ [::: ... List同士を連結する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L50)
+ [withFilter ... 中間データを作らない](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L56)
+ [view ... none-strict(中間データを作らない)なコレクションに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L66)
+ [lengthCompare ... コレクションの要素数と引数の長さを比較する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L73)
+ [lift](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/ListSpec.scala#L83)

### Map - キーと値で構成されるコレクション

+ [Map() ... Mapを生成する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L10)
+ [Map.empty ... 空のMapを生成する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L14)
+ [() ... Mapから値を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L19)
+ [+ ... 指定したキーと値のペアを追加したMapを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L26)
+ [- ... 指定したキーの要素を抜いたMapを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L31)
+ [ミュータブルなMap](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L36)
+ [keySet ... キーのSetを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L43)
+ [values ... 値をIterableで返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/MapSpec.scala#L48)

### Ninety-Nine Scala Problems

+ [P01 (*) Find the last element of a list.](legacy/src/test/scala/org/nomadblacky/scala/samples/s99/S99Spec.scala#L15)
+ [P02 (*) Find the last but one element of a list.](legacy/src/test/scala/org/nomadblacky/scala/samples/s99/S99Spec.scala#L21)
+ [P03 (*) Find the Kth element of a list.](legacy/src/test/scala/org/nomadblacky/scala/samples/s99/S99Spec.scala#L42)

### Option - 値が存在しない可能性があることを表すクラス

+ [Optionの基本](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L12)
+ [Optionから値を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L27)
+ [foreach ... Optionに値が含まれる場合のみに実行させる](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L46)
+ [map ... 中身の値を関数に適用し値を変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L60)
+ [flatMap ... 中身の値を関数に適用し、SomeならSomeを、NoneならNoneを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L68)
+ [collect ... PartialFunctionを適用し、値が返る場合はその結果をSomeに包んで返す](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L78)
+ [fold ... Noneなら初期値を、Someなら関数を適用した値を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/OptionSpec.scala#L100)

### Play WS ... Play製のHTTPクライアント

+ [基本的なHTTPアクセス](legacy/src/test/scala/org/nomadblacky/scala/samples/play/ws/PlayWSSpec.scala#L23)
+ [akka-streamsのSourceとして受け取る](legacy/src/test/scala/org/nomadblacky/scala/samples/play/ws/PlayWSSpec.scala#L32)

### Readable Code in Scala ~ for式編

+ [for式のおさらい for式は withFilter,flatMap,map,foreach のシュガーシンタックス](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L24)
+ [for式のおさらい mapの展開](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L57)
+ [for式のおさらい withFilterの展開](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L65)
+ [for式のおさらい flatMapの展開](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L75)
+ [for式のおさらい foreachの展開](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L88)
+ [for式のおさらい for式を紐解いてみよう](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L101)
+ [本当にあった怖いfor式 恐ろしく長いfor式](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L135)
+ [本当にあった怖いfor式 Bad Practice: ジェネレータに処理を詰め込む](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L170)
+ [本当にあった怖いfor式 内部関数やprivateメソッドに切り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L187)
+ [本当にあった怖いfor式 複数のfor式に書き換える](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N02ForSyntax.scala#L211)

### Readable Code in Scala ~ パターンマッチ編

+ [match式おさらい](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L13)
+ [愚直に実装する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L38)
+ [パターンマッチを使ってみる](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L65)
+ [collectを使う](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L91)
+ [PartialFunctionとは](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L101)
+ [標準ライブラリにおけるPartialFunctionの利用例 TraversableOnce#collectFirst](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L142)
+ [標準ライブラリにおけるPartialFunctionの利用例 Try#recoverWith](legacy/src/test/scala/org/nomadblacky/scala/samples/scala_kansai/y2018/N01PatternMatching.scala#L160)

### ScalaCheckSpec

+ [forAll ... ランダムに生成された値でテストを行う](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L9)
+ [whenever ... 値のフィルタ](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L15)
+ [Gen ... 値を生成するジェネレータ](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L27)
+ [Gen.oneOf ... 複数の要素からひとつを選択する](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L46)
+ [Gen.someOf ... 複数の要素から0個以上選択する](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L51)
+ [Gen.choose ... 範囲の中からひとつ選択する](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L56)
+ [Gen.alphaNumStr ... ランダムな文字列を生成する](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L61)
+ [Gen#suchThat ... ジェネレータに対するフィルタ](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L72)
+ [Arbitrary ... forAllのimplicit parameterとして使う](legacy/src/test/scala/org/nomadblacky/scala/samples/testing/ScalaCheckSpec.scala#L89)

### Scalaで列挙型を扱う

+ [Enumerationを使った方法](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/EnumInScalaSpec.scala#L9)
+ [余計な `Value` を排除する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/EnumInScalaSpec.scala#L39)
+ [列挙型に値をもたせる](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/EnumInScalaSpec.scala#L54)
+ [sealed trait と case objectを使った列挙型](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/EnumInScalaSpec.scala#L82)
+ [sealed abstract classで値と振る舞いをもたせる](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/EnumInScalaSpec.scala#L97)

### ScalaとJavaの結合

+ [ScalaからJavaを使う 01](legacy/src/test/scala/org/nomadblacky/scala/samples/with_java/WithJavaSpec.scala#L9)
+ [ScalaからJavaを使う 02](legacy/src/test/scala/org/nomadblacky/scala/samples/with_java/WithJavaSpec.scala#L14)

### Scalaのクラス

+ [require ... 引数を検証する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L12)
+ [unapplyを使用したパターンマッチ](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L24)
+ [caseクラスとパターンマッチ](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L50)
+ [抽象クラス](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L68)
+ [メソッドをvalでオーバーライドする](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L85)
+ [sealedによる継承制限とパターンマッチ](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ClassSpec.scala#L100)

### Scalaの関数

+ [関数リテラル](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L9)
+ [Functionトレイト](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L14)
+ [関数を引数に取る関数](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L19)
+ [プレースホルダ構文](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L25)
+ [プレースホルダ構文2](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L38)
+ [関数の部分適用](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L44)
+ [関数のカリー化](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L55)
+ [関数のカリー化2](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L66)
+ [関数の引数を遅延評価する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L73)
+ [scratch01](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L88)
+ [scratch01 by Mr.aiya000](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/FunctionSpec.scala#L120)

### ScalikeJDBC

+ [接続設定とコネクション コネクションプールの設定](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L44)
+ [接続設定とコネクション 複数データソースへの接続](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L53)
+ [接続設定とコネクション その他オプションの設定](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L67)
+ [DBブロックとトランザクション readOnly ... select文のみ実行できる](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L85)
+ [DBブロックとトランザクション autoCommit ... 更新毎にコミットを行う](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L98)
+ [DBブロックとトランザクション localTx ... ブロックのスコープに閉じた同一トランザクションで実行する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L107)
+ [DBブロックとトランザクション withTx ... すでに存在するトランザクション内で実行する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L126)
+ [DBブロックとトランザクション AutoSession ... 有効なセッションがなければ自動的に新しいセッションを開始する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L149)
+ [SQLテンプレート JDBCのSQLテンプレート](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L181)
+ [SQLテンプレート 名前付きSQLテンプレート](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L186)
+ [SQLテンプレート 実行可能なSQLテンプレート](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L194)
+ [SQLテンプレート SQLインターポレーション](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L207)
+ [SQLテンプレート SQLSyntax](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L214)
+ [SQLテンプレート Seqの展開](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L224)
+ [SQLテンプレート SQLSyntaxSupport](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L230)
+ [SQLテンプレート QueryDSL](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L267)
+ [SQLテンプレート [Tips] SQLSyntaxSupportFeature#selectDynamic におけるカラム名展開の違い](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L292)
+ [一般的な利用のサンプル例 single ... Primary Key での検索](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L313)
+ [一般的な利用のサンプル例 count文 & single ... 件数を取得](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L323)
+ [一般的な利用のサンプル例 list ... 複数件取得](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L330)
+ [一般的な利用のサンプル例 first ... 最初の１件のみ取得](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L336)
+ [一般的な利用のサンプル例 foreach ... １行ずつ読み込む](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L342)
+ [一般的な利用のサンプル例 in句 と SQLInterpolation](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L351)
+ [一般的な利用のサンプル例 join クエリ](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L366)
+ [一般的な利用のサンプル例 Insert](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L393)
+ [一般的な利用のサンプル例 auto-increment な id を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L405)
+ [一般的な利用のサンプル例 Update](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L415)
+ [一般的な利用のサンプル例 Delete](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L423)
+ [一般的な利用のサンプル例 Batch](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L431)
+ [SQL ロギング コードでの設定](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L463)
+ [SQL ロギング SLF4Jの実装を設定](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L474)
+ [SQL ロギング シングルラインモード](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L490)
+ [Skinny ORM SkinnyCRUDMapper](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCSpec.scala#L502)

### ScalikeJDBCでのユニットテスト

+ [ユニットテスト 接続情報の設定](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCUnitTestSpec.scala#L40)
+ [ユニットテスト 自動ロールバック](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalikejdbc/ScalikeJDBCUnitTestSpec.scala#L48)

### Scopt - コマンドライン引数を解析するライブラリ

+ [コマンドライン引数を解析する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scopt/ScoptSpec.scala#L45)
+ [引数から日付を取得](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scopt/ScoptSpec.scala#L54)
+ [引数からファイルを取得](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scopt/ScoptSpec.scala#L64)

### Set - 重複する要素を含まないコレクション

+ [apply, contains ... 要素が含まれるかを調べる](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L18)
+ [subsetOf ... 部分集合であるか調べる](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L25)
+ [+ ... 渡された要素を追加した集合を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L31)
+ [++ ... 渡された集合を追加した集合を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L37)
+ [- ... 渡された要素を除いた集合を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L41)
+ [-- ... 渡された集合のすべての要素を除いた集合を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L47)
+ [empty ... 集合と同じクラスの空集合を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L52)
+ [&, intersect ... 積集合](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L57)
+ [|, union ... 和集合](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L62)
+ [&~, diff ... 差集合](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/SetSpec.scala#L67)

### Shapeless

+ [Poly ... 複数の型を処理できる関数](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L14)
+ [HList ... 複数の型を持てるList](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L30)
+ [HListの操作](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L43)
+ [Coproduct ... Eitherを任意の数の選択肢にしたもの](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L59)
+ [Generic ... case classなどをHListやCoproductに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L90)
+ [Record ... HListにキーがついたもの](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L111)
+ [Sindleton-typed literals ... リテラルをひとつの型として扱う](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L128)
+ [case classをMapに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/shapeless/ShapelessSpec.scala#L140)

### Traversable - コレクションの最上位に位置するトレイト

+ [++ ... Traversableを連結する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L16)
+ [head / headOptional ... 先頭の要素を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L20)
+ [last / lastOption ... 最後の要素を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L28)
+ [init ... 最後以外の要素を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L36)
+ [tail ... 最初以外の要素を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L43)
+ [foldLeft ... 要素の先頭から畳み込みを行う](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L50)
+ [foldRight ... 要素の最後から畳み込みを行う](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L64)
+ [reduceLeft ... 最初の要素を初期値として畳み込みを行う](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L79)
+ [reduceRight ... 最後の要素を初期値として畳み込みを行う](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L88)
+ [foreach ... 戻り値なしで全ての要素を処理する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L98)
+ [filter ... 条件に一致する要素のみを抜き出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L106)
+ [filter ... 条件に一致しない要素のみを抜き出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L111)
+ [drop ... 指定した数の要素を先頭から取り除く](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L116)
+ [dropWhile ... 条件がfalseになるまで要素を取り除く](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L124)
+ [take ... 指定した数の要素を先頭から取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L131)
+ [takeWhile ... 条件がfalseになるまで要素を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L139)
+ [map ... 要素に関数を適用して新しいコレクションを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L146)
+ [flatMap ... 要素に関数を適用して新しいコレクションを返しflattenする](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L150)
+ [flatten ... 入れ子になったコレクションを1次元にする](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L154)
+ [splitAt ... コレクションを分割する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L158)
+ [slice ... コレクションの一部を抜き出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L162)
+ [partition ... 条件を満たす要素とそうでない要素に分割する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L166)
+ [span ... 条件がfalseとなった要素を堺にコレクションを分割する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L171)
+ [groupBy ... 関数の結果をキーとして要素をMapにして返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L176)
+ [grouped ... N件ごとにコレクションを分割する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L180)
+ [unzip ... 要素を2つのコレクションに分割する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L187)
+ [unzip3 ... 要素を3つのコレクションに分割する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L192)
+ [find ... 最初に条件を満たした要素をOptionで返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L202)
+ [exists ... 条件を満たす要素があるか判定する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L208)
+ [forall ... 全ての要素が条件を満たすか判定する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L213)
+ [count ... 条件を満たす要素の個数を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L218)
+ [size/length ... 要素の個数を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L223)
+ [min ... 最小値を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L228)
+ [max ... 最大値を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L233)
+ [sum ... 合計値を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L238)
+ [mkString ... コレクションから文字列を作る](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L244)
+ [toArray ... コレクションをArrayに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L250)
+ [toBuffer ... コレクションをBufferに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L256)
+ [toIndexedSeq ... コレクションをIndexedSeqに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L260)
+ [toList ... コレクションをListに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L264)
+ [toStream ... コレクションをStreamに変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L268)
+ [par ... 並列コレクション(ParIterable)に変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L272)
+ [view/force ... コレクションの操作を遅延評価させる(中間データを作らない)](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L276)
+ [collect ... PartialFunctionを適用して要素を変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L280)
+ [collectFirst ... PartialFunctionに最初に一致した値を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TraversableSpec.scala#L291)

### Tuple - 任意の複数の値を保持するクラス

+ [タプルを生成する](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TupleSpec.scala#L9)
+ [タプルから値を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TupleSpec.scala#L13)
+ [タプルの要素に意味付けをする](legacy/src/test/scala/org/nomadblacky/scala/samples/collections/TupleSpec.scala#L20)

### TypeParameterSpec

+ [Scalaにおける型の検査](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TypeParameterSpec.scala#L10)

### XMLを扱う

+ [xmlリテラル](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L11)
+ [値を埋め込む](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L17)
+ [ファイルから読み込む](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L23)
+ [要素を取得する1](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L29)
+ [要素を取得する2](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L35)
+ [要素を取得する3](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L42)
+ [属性から要素を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/xml/XmlSpec.scala#L49)

### [BestPractice] Scalaでのデザインパターン

+ [Pimp My Library パターンで既存クラスにメソッドを追加する](legacy/src/test/scala/org/nomadblacky/scala/samples/best_practice/DesignPatternsInScalaSpec.scala#L15)
+ [LoanパターンでAutoClosingを実装する](legacy/src/test/scala/org/nomadblacky/scala/samples/best_practice/DesignPatternsInScalaSpec.scala#L28)

### [Cats] Catsの基礎

+ [cats.syntax で型クラス・既存型に対する拡張などが提供される](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/cats/CatsSpec.scala#L11)
+ [cats.instances で型クラスの実装が提供される](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/cats/CatsSpec.scala#L30)
+ [Eq ... 型安全な等価比較を提供する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/cats/CatsSpec.scala#L46)
+ [Monoid ... 二項演算と単位元を持つ代数的構造](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/cats/CatsSpec.scala#L61)

### [FP in Scala] 第10章 モノイド

+ [[EXERCISE 10.1] 整数の加算、乗算、論理演算子に対するMonoidインスタンス](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter10/Chapter10Spec.scala#L36)
+ [[EXERCISE 10.2] Option型の値を結合するMonoidインスタンス](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter10/Chapter10Spec.scala#L88)
+ [[EXERCISE 10.3] endo関数のモノイド](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter10/Chapter10Spec.scala#L127)
+ [[EXERCISE 10.4] foldMapの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter10/Chapter10Spec.scala#L141)

### [FP in Scala] 第11章 モナド

+ [11.1 ファンクタ : map関数の一般化](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter11/Chapter11Spec.scala#L19)

### [FP in Scala] 第2章 Scala関数型プログラミングの準備

+ [[EXERCISE 2.1] フィボナッチ数](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter02/Chapter02Spec.scala#L15)
+ [[EXERCISE 2.1] フィボナッチ数(Stream)](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter02/Chapter02Spec.scala#L31)
+ [[EXERCISE 2.2] isSortedの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter02/Chapter02Spec.scala#L40)
+ [[EXERCISE 2.3] カリー化](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter02/Chapter02Spec.scala#L57)
+ [[EXERCISE 2.4] 逆カリー化](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter02/Chapter02Spec.scala#L71)
+ [[EXERCISE 2.5] 関数の合成](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter02/Chapter02Spec.scala#L85)

### [FP in Scala] 第3章 関数型プログラミングのデータ構造

+ [[EXERCISE 3.1] match式](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L15)
+ [[EXERCISE 3.2] tailの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L27)
+ [[EXERCISE 3.3] setHeadの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L34)
+ [[EXERCISE 3.4] dropの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L41)
+ [[EXERCISE 3.5] dropWhileの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L49)
+ [[EXERCISE 3.6] initの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L63)
+ [[EXERCISE 3.9] lengthの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L70)
+ [[EXERCISE 3.10] foldLeftの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L77)
+ [[EXERCISE 3.11] foldLeftを使った、sum, product, lengthの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L82)
+ [[EXERCISE 3.12] reverseの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L88)
+ [[EXERCISE 3.14] foldRitghtを利用したappendの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L93)
+ [[EXERCISE 3.15] flattenの実装](legacy/src/test/scala/org/nomadblacky/scala/samples/functional/programming/in/scala/chapter03/Chapter03Spec.scala#L97)

### [Scalaz] Disjunction - 強化版Either

+ [Left,Rightの生成](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/DisjunctionSpec.scala#L22)

### [Scalaz] NonEmptyList - 空でないことが保証されるリスト

+ [<:: ．．． 先頭に要素を追加する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/NonEmptyListSpec.scala#L19)
+ [head ... 先頭の要素を取り出す](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/NonEmptyListSpec.scala#L23)
+ [size ... 要素の数を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/NonEmptyListSpec.scala#L27)
+ [reverse ... リストを反転する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/NonEmptyListSpec.scala#L31)
+ [map ... 要素に関数を適用する](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/NonEmptyListSpec.scala#L35)
+ [flatmap ... 要素に関数を適用し、flattenする](legacy/src/test/scala/org/nomadblacky/scala/samples/libraries/scalaz/NonEmptyListSpec.scala#L39)

### [勉強会] Understanding Scala - Scalaのimplicit parameterを学ぶ

+ [implicit parameter](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaImplicitSpec.scala#L16)
+ [Monoidを使ってsumを書き直す](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaImplicitSpec.scala#L37)
+ [implicit parameterの導入](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaImplicitSpec.scala#L58)
+ [implicit parameterの仕組み](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaImplicitSpec.scala#L80)
+ [Scala標準ライブラリにおけるimplicit parameterの例](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaImplicitSpec.scala#L90)

### [勉強会] Understanding Scala - Scalaの型システムを学ぶ

+ [Any あらゆる型のスーパータイプ](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L19)
+ [AnyVal: あらゆる値型のスーパータイプ](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L32)
+ [AnyRef: あらゆる参照型のスーパータイプ](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L48)
+ [Nothing: あらゆる型のサブタイプ](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L61)
+ [Null](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L75)
+ [ジェネリクス](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L87)
+ [共変](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L99)
+ [反変](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L132)
+ [構造的部分型](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L173)
+ [高階多相](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTypeSpec.scala#L199)

### [勉強会] Understanding Scala - Scalaの実行時の挙動を学ぶ

+ [メソッド呼び出し式](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L18)
+ [while式](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L29)
+ [if式](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L41)
+ [for式(1)](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L52)
+ [for式(2)](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L71)
+ [for式(3)](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L83)
+ [for式(4)](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L96)
+ [match式](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaFormulaSpec.scala#L110)

### [勉強会] Understanding Scala - Scalaの落とし穴を学ぶ

+ [Unitを返す関数を意図せず書いてしまう](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L18)
+ [変更可能コレクションの変更しない操作を呼び出してしまう](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L32)
+ [配列同士の==](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L38)
+ [誤ったFutureの使い方](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L48)
+ [意図しない結果のパターンマッチ](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L65)
+ [誤った正規表現のパターンマッチ](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L69)
+ [既存の型同士のimplicit conversionは使わない](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L73)
+ [改行とブロックの組み合わせに注意](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L84)
+ [Javaのメソッドの返り値に注意](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L88)
+ [Set#mapの罠](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L94)
+ [インナークラスのインスタンス](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L101)
+ [アンダースコア七変化](legacy/src/test/scala/org/nomadblacky/scala/samples/event/understanding_scala/UnderstandingScalaTrapSpec.scala#L110)

### for式 (for内包表記)

+ [コレクションのイテレート](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L19)
+ [要素のフィルタ](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L25)
+ [yield ... forの結果を新しいコレクションとして返す](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L33)
+ [for式のforeach展開](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L38)
+ [for式のmap展開](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L53)
+ [ジェネレータが1個のときの変換](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L70)
+ [1個のジェネレータと1個のフィルターで始まるfor式の変換](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L76)
+ [2個のジェネレータで始まるfor式の変換](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L84)
+ [ジェネレータに含まれるパターンの変換 - タプルの場合](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L95)
+ [ジェネレータに含まれるパターンの変換 - その他パターンの場合](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L101)
+ [[Sample] 2つのコレクションを同じ順序で取り出して処理する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/ForSpec.scala#L113)

### match式とパターンマッチング

+ [基本的なマッチング](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L18)
+ [型のマッチング](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L30)
+ [パターンガード](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L42)
+ [リストのマッチング](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L53)
+ [複数のパターンをまとめる](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L63)
+ [パターンマッチでFizzBuzz](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L82)
+ [unapply ... 独自のパターンを定義する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/MatchSpec.scala#L113)

### scala.Dynamic ... 動的言語のような構文をサポートする

+ [applyDynamic](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/DynamicSpec.scala#L16)
+ [applyDynamicNamed](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/DynamicSpec.scala#L27)
+ [selectDynamic](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/DynamicSpec.scala#L45)
+ [updateDynamic](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/DynamicSpec.scala#L55)

### トレイトの使い方

+ [トレイトの基本](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L17)
+ [複数のトレイトをミックスインする](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L28)
+ [インスタンス化のタイミングでミックスインする](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L44)
+ [同じシグネチャのメソッドを複数ミックスインした場合](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L55)
+ [superで呼び出すトレイトのメソッドを指定する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L69)
+ [トレイトを単体で利用する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L89)
+ [abstract override で既存のメソッドに新しい処理を追加する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L108)
+ [トレイトの指定順序](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L132)
+ [自分型アノテーション](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TraitSpec.scala#L163)

### パーザコンビネータ

+ [電話番号のパース](legacy/src/test/scala/org/nomadblacky/scala/samples/parsercombinator/ParserCombinatorSpec.scala#L19)
+ [パースのエラーハンドリング](legacy/src/test/scala/org/nomadblacky/scala/samples/parsercombinator/ParserCombinatorSpec.scala#L30)
+ [詳細なエラー内容を取得する](legacy/src/test/scala/org/nomadblacky/scala/samples/parsercombinator/ParserCombinatorSpec.scala#L51)
+ [パース内容を変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/parsercombinator/ParserCombinatorSpec.scala#L68)
+ [四則演算のパース](legacy/src/test/scala/org/nomadblacky/scala/samples/parsercombinator/ParserCombinatorSpec.scala#L90)

### 例外処理

+ [基本的なtry-catch-finally](legacy/src/test/scala/org/nomadblacky/scala/samples/exceptions/ExceptionSpec.scala#L21)

### 型クラスの使い方

+ [型クラスとは](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TypeClassSpec.scala#L26)
+ [型クラスの例](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TypeClassSpec.scala#L44)
+ [Orderedを使った実装例](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/TypeClassSpec.scala#L66)

### 文字列の補間 (String Interpolation)

+ [s補間子](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/StringInterpolationSpec.scala#L13)
+ [f補間子](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/StringInterpolationSpec.scala#L20)
+ [raw補間子](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/StringInterpolationSpec.scala#L27)
+ [自分で実装する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/StringInterpolationSpec.scala#L33)

### 言語処理100本ノック 第1章: 準備運動

+ [00. 文字列の逆順](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L19)
+ [01. 「パタトクカシーー」](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L24)
+ [02. 「パトカー」＋「タクシー」＝「パタトクカシーー」](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L53)
+ [03. 円周率](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L119)
+ [04. 元素記号](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L164)
+ [07. テンプレートによる文生成](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L234)
+ [08. 暗号文](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L254)
+ [09. Typoglycemia](legacy/src/test/scala/org/nomadblacky/scala/samples/nlp100/Chapter01Spec.scala#L300)

### 遅延評価

+ [lazy ... 変数を遅延評価する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/LazySpec.scala#L9)

### 部分関数

+ [PartialFunctionを定義する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L19)
+ [caseはPartialFunctionのシンタックスシュガー](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L30)
+ [isDefinedAt ... 引数に対して値が返される場合はtrueを返す](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L58)
+ [andThen ... PartialFunction合成する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L68)
+ [compose ... PartialFunctionを合成する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L85)
+ [orElse ... 部分関数にマッチしなかった引数を次の部分関数にマッチさせる関数合成](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L102)
+ [runWith ... 部分関数の結果を利用する関数と合成する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L120)
+ [lift ... 関数の結果をOptionで返す関数に変換する](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L128)
+ [applyOrElse ... 引数がマッチすればその結果を返し、マッチしなければデフォルト値を返す](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L139)
+ [[Usage] ふつうの関数(全域関数)の代わりに使う](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L151)
+ [[Usage] TraversableLike#collect](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L167)
+ [[Usage] TraversableOnce#collectFirst](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L178)
+ [[Usage] Try#collect](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L187)
+ [ListはPartialFunction[Int,A]をmix-inしている](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L197)
+ [MapはPartialFunction[K,V]をmix-inしている](legacy/src/test/scala/org/nomadblacky/scala/samples/scala/PartialFunctionSpec.scala#L207)
