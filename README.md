# Table of Contents


## Akka HTTP

+ クライアントAPI

## Akka Streams

+ ことはじめ

## Ammonite-Ops

+ パスを参照する
+ 基本的なファイル操作
+ ファイルの読み書き
+ 拡張演算子とワンライナー
+ サブプロセスの実行

## Chapter02Spec

+ テキストファイルの読み込み
+ 10. 行数のカウント

## Chapter06Spec

+ [EXERCISE 6.1] ランダムな 0~Int.MaxValue のIntを生成する関数
+ [EXERCISE 6.2] ランダムな 0~1未満 のDoubleを生成する関数
+ [EXERCISE 6.3] ランダムな値のタプルを生成する関数
+ [EXERCISE 6.4] ランダムな整数のリストを作成する関数
+ [EXERCISE 6.5] double関数の再実装
+ [EXERCISE 6.6] mapの再実装
+ [EXERCISE 6.7] sequenceの実装
+ [EXERCISE 6.8] flatMapの実装
+ [EXERCISE 6.9] map,map2の再実装
+ 6.5 状態アクションデータ型の一般化 [EXERCISE 6.10] 関数の一般化
+ 6.6 純粋関数型の命令型プログラミング [EXERCISE 6.11] 有限状態オートマトンの実装

## EffectiveScalaSpec

+ 関数定義の中で直接パターンマッチを使う
+ 型エイリアスを使う
+ コレクション処理の可読性を保つ
+ Javaコレクションとの相互変換

## Futureの使い方

+ Futureの基本
+ Await ... スレッドの終了を待機する
+ onComplete ... コールバック関数を定義する
+ map ... Futureの計算結果を処理するFutureを取得する
+ sequence ... Seq[Future] を Future[Seq] に変換する

## Iterable - コレクションの要素をひとつずつ返すトレイト

+ grouped ... 指定サイズのListにまとめたIteratorを返す
+ sliding ... ウィンドウをずらしながら参照するIteratorを返す
+ takeRight ... コレクションの最後のn個の要素を取り出す
+ dropRight ... コレクションの最後のn個の要素を取り除く
+ zip ... 2つのコレクションから対応する要素をペアにする
+ zipAll ... 2つのコレクションから対応する要素をペアにする
+ zipWithIndex ... コレクションの要素と添字をペアにしたIterableを返す
+ sameElements ... 2つのコレクションが同じ要素を同じ順序で格納しているかを返す

## JFreeChart - グラフを描画するJavaライブラリ

+ Part1 ... 円グラフを作成する
+ Part2 ... ヒストグラム

## JVM関連のあれこれ

+ クラスパスの一覧を取得する

## List - 単方向リスト

+ List() ... Listを生成する
+ () ... Listから値を取り出す
+ Nil ... 空のListを作成する
+ ListBuffer ... ミュータブルなList
+ :: :+ ... Listに値を追加する
+ ::: ... List同士を連結する
+ withFilter ... 中間データを作らない
+ view ... none-strict(中間データを作らない)なコレクションに変換する
+ lengthCompare ... コレクションの要素数と引数の長さを比較する
+ lift

## Map - キーと値で構成されるコレクション

+ Map() ... Mapを生成する
+ Map.empty ... 空のMapを生成する
+ () ... Mapから値を取り出す
+ + ... 指定したキーと値のペアを追加したMapを返す
+ - ... 指定したキーの要素を抜いたMapを返す
+ ミュータブルなMap
+ keySet ... キーのSetを返す
+ values ... 値をIterableで返す

## Ninety-Nine Scala Problems

+ P01 (*) Find the last element of a list.
+ P02 (*) Find the last but one element of a list.
+ P03 (*) Find the Kth element of a list.

## Option - 値が存在しない可能性があることを表すクラス

+ Optionの基本
+ Optionから値を取り出す
+ foreach ... Optionに値が含まれる場合のみに実行させる
+ map ... 中身の値を関数に適用し値を変換する
+ flatMap ... 中身の値を関数に適用し、SomeならSomeを、NoneならNoneを返す
+ collect ... PartialFunctionを適用し、値が返る場合はその結果をSomeに包んで返す
+ fold ... Noneなら初期値を、Someなら関数を適用した値を返す

## Play WS ... Play製のHTTPクライアント

+ 基本的なHTTPアクセス
+ akka-streamsのSourceとして受け取る

## ScalaCheckSpec

+ forAll ... ランダムに生成された値でテストを行う
+ whenever ... 値のフィルタ
+ Gen ... 値を生成するジェネレータ
+ Gen.oneOf ... 複数の要素からひとつを選択する
+ Gen.someOf ... 複数の要素から0個以上選択する
+ Gen.choose ... 範囲の中からひとつ選択する
+ Gen.alphaNumStr ... ランダムな文字列を生成する
+ Gen#suchThat ... ジェネレータに対するフィルタ
+ Arbitrary ... forAllのimplicit parameterとして使う

## Scalaで列挙型を扱う

+ Enumerationを使った方法
+ 余計な `Value` を排除する
+ 列挙型に値をもたせる
+ sealed trait と case objectを使った列挙型
+ sealed abstract classで値と振る舞いをもたせる

## ScalaとJavaの結合

+ ScalaからJavaを使う 01
+ ScalaからJavaを使う 02

## Scalaのクラス

+ require ... 引数を検証する
+ unapplyを使用したパターンマッチ
+ caseクラスとパターンマッチ
+ 抽象クラス
+ メソッドをvalでオーバーライドする
+ sealedによる継承制限とパターンマッチ

## Scalaの関数

+ 関数リテラル
+ Functionトレイト
+ 関数を引数に取る関数
+ プレースホルダ構文
+ プレースホルダ構文2
+ 関数の部分適用
+ 関数のカリー化
+ 関数のカリー化2
+ 関数の引数を遅延評価する
+ scratch01
+ scratch01 by Mr.aiya000

## ScalikeJDBC

+ 接続設定とコネクション コネクションプールの設定
+ 接続設定とコネクション 複数データソースへの接続
+ 接続設定とコネクション その他オプションの設定
+ DBブロックとトランザクション readOnly ... select文のみ実行できる
+ DBブロックとトランザクション autoCommit ... 更新毎にコミットを行う
+ DBブロックとトランザクション localTx ... ブロックのスコープに閉じた同一トランザクションで実行する
+ DBブロックとトランザクション withTx ... すでに存在するトランザクション内で実行する
+ DBブロックとトランザクション AutoSession ... 有効なセッションがなければ自動的に新しいセッションを開始する
+ SQLテンプレート JDBCのSQLテンプレート
+ SQLテンプレート 名前付きSQLテンプレート
+ SQLテンプレート 実行可能なSQLテンプレート
+ SQLテンプレート SQLインターポレーション
+ SQLテンプレート SQLSyntax
+ SQLテンプレート Seqの展開
+ SQLテンプレート SQLSyntaxSupport
+ SQLテンプレート QueryDSL
+ SQLテンプレート [Tips] SQLSyntaxSupportFeature#selectDynamic におけるカラム名展開の違い
+ 一般的な利用のサンプル例 single ... Primary Key での検索
+ 一般的な利用のサンプル例 count文 & single ... 件数を取得
+ 一般的な利用のサンプル例 list ... 複数件取得
+ 一般的な利用のサンプル例 first ... 最初の１件のみ取得
+ 一般的な利用のサンプル例 foreach ... １行ずつ読み込む
+ 一般的な利用のサンプル例 in句 と SQLInterpolation
+ 一般的な利用のサンプル例 join クエリ
+ 一般的な利用のサンプル例 Insert
+ 一般的な利用のサンプル例 auto-increment な id を取得する
+ 一般的な利用のサンプル例 Update
+ 一般的な利用のサンプル例 Delete
+ 一般的な利用のサンプル例 Batch
+ SQL ロギング コードでの設定
+ SQL ロギング SLF4Jの実装を設定
+ SQL ロギング シングルラインモード
+ Skinny ORM SkinnyCRUDMapper

## ScalikeJDBCでのユニットテスト

+ ユニットテスト 接続情報の設定
+ ユニットテスト 自動ロールバック

## Scopt - コマンドライン引数を解析するライブラリ

+ コマンドライン引数を解析する
+ 引数から日付を取得
+ 引数からファイルを取得

## Set - 重複する要素を含まないコレクション

+ apply, contains ... 要素が含まれるかを調べる
+ subsetOf ... 部分集合であるか調べる
+ + ... 渡された要素を追加した集合を返す
+ ++ ... 渡された集合を追加した集合を返す
+ - ... 渡された要素を除いた集合を返す
+ -- ... 渡された集合のすべての要素を除いた集合を返す
+ empty ... 集合と同じクラスの空集合を返す
+ &, intersect ... 積集合
+ |, union ... 和集合
+ &~, diff ... 差集合

## Shapeless

+ Poly ... 複数の型を処理できる関数
+ HList ... 複数の型を持てるList
+ HListの操作
+ Coproduct ... Eitherを任意の数の選択肢にしたもの
+ Generic ... case classなどをHListやCoproductに変換する
+ Record ... HListにキーがついたもの
+ Sindleton-typed literals ... リテラルをひとつの型として扱う
+ case classをMapに変換する

## Traversable - コレクションの最上位に位置するトレイト

+ ++ ... Traversableを連結する
+ head / headOptional ... 先頭の要素を取得する
+ last / lastOption ... 最後の要素を取得する
+ init ... 最後以外の要素を取得する
+ tail ... 最初以外の要素を取得する
+ foldLeft ... 要素の先頭から畳み込みを行う
+ foldRight ... 要素の最後から畳み込みを行う
+ reduceLeft ... 最初の要素を初期値として畳み込みを行う
+ reduceRight ... 最後の要素を初期値として畳み込みを行う
+ foreach ... 戻り値なしで全ての要素を処理する
+ filter ... 条件に一致する要素のみを抜き出す
+ filter ... 条件に一致しない要素のみを抜き出す
+ drop ... 指定した数の要素を先頭から取り除く
+ dropWhile ... 条件がfalseになるまで要素を取り除く
+ take ... 指定した数の要素を先頭から取り出す
+ takeWhile ... 条件がfalseになるまで要素を取り出す
+ map ... 要素に関数を適用して新しいコレクションを返す
+ flatMap ... 要素に関数を適用して新しいコレクションを返しflattenする
+ flatten ... 入れ子になったコレクションを1次元にする
+ splitAt ... コレクションを分割する
+ slice ... コレクションの一部を抜き出す
+ partition ... 条件を満たす要素とそうでない要素に分割する
+ span ... 条件がfalseとなった要素を堺にコレクションを分割する
+ groupBy ... 関数の結果をキーとして要素をMapにして返す
+ unzip ... 要素を2つのコレクションに分割する
+ unzip3 ... 要素を3つのコレクションに分割する
+ find ... 最初に条件を満たした要素をOptionで返す
+ exists ... 条件を満たす要素があるか判定する
+ forall ... 全ての要素が条件を満たすか判定する
+ count ... 条件を満たす要素の個数を返す
+ size/length ... 要素の個数を返す
+ min ... 最小値を返す
+ max ... 最大値を返す
+ sum ... 合計値を返す
+ mkString ... コレクションから文字列を作る
+ toArray ... コレクションをArrayに変換する
+ toBuffer ... コレクションをBufferに変換する
+ toIndexedSeq ... コレクションをIndexedSeqに変換する
+ toList ... コレクションをListに変換する
+ toStream ... コレクションをStreamに変換する
+ par ... 並列コレクション(ParIterable)に変換する
+ view/force ... コレクションの操作を遅延評価させる(中間データを作らない)
+ collect ... PartialFunctionを適用して要素を変換する
+ collectFirst ... PartialFunctionに最初に一致した値を取得する

## Tuple - 任意の複数の値を保持するクラス

+ タプルを生成する
+ タプルから値を取り出す
+ タプルの要素に意味付けをする

## TypeParameterSpec

+ Scalaにおける型の検査

## XMLを扱う

+ xmlリテラル
+ 値を埋め込む
+ ファイルから読み込む
+ 要素を取得する1
+ 要素を取得する2
+ 要素を取得する3
+ 属性から要素を取得する

## [BestPractice] Scalaでのデザインパターン

+ Pimp My Library パターンで既存クラスにメソッドを追加する
+ LoanパターンでAutoClosingを実装する

## [Cats] Catsの基礎

+ cats.syntax で型クラス・既存型に対する拡張などが提供される
+ cats.instances で型クラスの実装が提供される
+ Eq ... 型安全な等価比較を提供する
+ Monoid ... 二項演算と単位元を持つ代数的構造

## [FP in Scala] 第10章 モノイド

+ [EXERCISE 10.1] 整数の加算、乗算、論理演算子に対するMonoidインスタンス
+ [EXERCISE 10.2] Option型の値を結合するMonoidインスタンス
+ [EXERCISE 10.3] endo関数のモノイド
+ [EXERCISE 10.4] foldMapの実装

## [FP in Scala] 第11章 モナド

+ 11.1 ファンクタ : map関数の一般化

## [FP in Scala] 第2章 Scala関数型プログラミングの準備

+ [EXERCISE 2.1] フィボナッチ数
+ [EXERCISE 2.1] フィボナッチ数(Stream)
+ [EXERCISE 2.2] isSortedの実装
+ [EXERCISE 2.3] カリー化
+ [EXERCISE 2.4] 逆カリー化
+ [EXERCISE 2.5] 関数の合成

## [FP in Scala] 第3章 関数型プログラミングのデータ構造

+ [EXERCISE 3.1] match式
+ [EXERCISE 3.2] tailの実装
+ [EXERCISE 3.3] setHeadの実装
+ [EXERCISE 3.4] dropの実装
+ [EXERCISE 3.5] dropWhileの実装
+ [EXERCISE 3.6] initの実装
+ [EXERCISE 3.9] lengthの実装
+ [EXERCISE 3.10] foldLeftの実装
+ [EXERCISE 3.11] foldLeftを使った、sum, product, lengthの実装
+ [EXERCISE 3.12] reverseの実装
+ [EXERCISE 3.14] foldRitghtを利用したappendの実装
+ [EXERCISE 3.15] flattenの実装

## [Scalaz] Disjunction - 強化版Either

+ Left,Rightの生成

## [Scalaz] NonEmptyList - 空でないことが保証されるリスト

+ <:: ．．． 先頭に要素を追加する
+ head ... 先頭の要素を取り出す
+ size ... 要素の数を取得する
+ reverse ... リストを反転する
+ map ... 要素に関数を適用する
+ flatmap ... 要素に関数を適用し、flattenする

## [勉強会] Understanding Scala - Scalaのimplicit parameterを学ぶ

+ implicit parameter
+ Monoidを使ってsumを書き直す
+ implicit parameterの導入
+ implicit parameterの仕組み
+ Scala標準ライブラリにおけるimplicit parameterの例

## [勉強会] Understanding Scala - Scalaの型システムを学ぶ

+ Any あらゆる型のスーパータイプ
+ AnyVal: あらゆる値型のスーパータイプ
+ AnyRef: あらゆる参照型のスーパータイプ
+ Nothing: あらゆる型のサブタイプ
+ Null
+ ジェネリクス
+ 共変
+ 反変
+ 構造的部分型
+ 高階多相

## [勉強会] Understanding Scala - Scalaの実行時の挙動を学ぶ

+ メソッド呼び出し式
+ while式
+ if式
+ for式(1)
+ for式(2)
+ for式(3)
+ for式(4)
+ match式

## [勉強会] Understanding Scala - Scalaの落とし穴を学ぶ

+ Unitを返す関数を意図せず書いてしまう
+ 変更可能コレクションの変更しない操作を呼び出してしまう
+ 配列同士の==
+ 誤ったFutureの使い方
+ 意図しない結果のパターンマッチ
+ 誤った正規表現のパターンマッチ
+ 既存の型同士のimplicit conversionは使わない
+ 改行とブロックの組み合わせに注意
+ Javaのメソッドの返り値に注意
+ Set#mapの罠
+ インナークラスのインスタンス
+ アンダースコア七変化

## for式 (for内包表記)

+ コレクションのイテレート
+ 要素のフィルタ
+ yield ... forの結果を新しいコレクションとして返す
+ for式のforeach展開
+ for式のmap展開
+ ジェネレータが1個のときの変換
+ 1個のジェネレータと1個のフィルターで始まるfor式の変換
+ 2個のジェネレータで始まるfor式の変換
+ ジェネレータに含まれるパターンの変換 - タプルの場合
+ ジェネレータに含まれるパターンの変換 - その他パターンの場合
+ [Sample] 2つのコレクションを同じ順序で取り出して処理する

## match式とパターンマッチング

+ 基本的なマッチング
+ 型のマッチング
+ パターンガード
+ リストのマッチング
+ 複数のパターンをまとめる
+ パターンマッチでFizzBuzz
+ unapply ... 独自のパターンを定義する

## scala.Dynamic ... 動的言語のような構文をサポートする

+ applyDynamic
+ applyDynamicNamed
+ selectDynamic
+ updateDynamic

## トレイトの使い方

+ トレイトの基本
+ 複数のトレイトをミックスインする
+ インスタンス化のタイミングでミックスインする
+ 同じシグネチャのメソッドを複数ミックスインした場合
+ superで呼び出すトレイトのメソッドを指定する
+ トレイトを単体で利用する
+ abstract override で既存のメソッドに新しい処理を追加する
+ トレイトの指定順序
+ 自分型アノテーション

## パーザコンビネータ

+ 電話番号のパース
+ パースのエラーハンドリング
+ 詳細なエラー内容を取得する
+ パース内容を変換する
+ 四則演算のパース

## 例外処理

+ 基本的なtry-catch-finally

## 型クラスの使い方

+ 型クラスとは
+ 型クラスの例
+ Orderedを使った実装例

## 文字列の補間 (String Interpolation)

+ s補間子
+ f補間子
+ raw補間子
+ 自分で実装する

## 言語処理100本ノック 第1章: 準備運動

+ 00. 文字列の逆順
+ 01. 「パタトクカシーー」
+ 02. 「パトカー」＋「タクシー」＝「パタトクカシーー」
+ 03. 円周率
+ 04. 元素記号
+ 07. テンプレートによる文生成
+ 08. 暗号文
+ 09. Typoglycemia

## 遅延評価

+ lazy ... 変数を遅延評価する

## 部分関数

+ PartialFunctionを定義する
+ caseはPartialFunctionのシンタックスシュガー
+ isDefinedAt ... 引数に対して値が返される場合はtrueを返す
+ andThen ... PartialFunction合成する
+ compose ... PartialFunctionを合成する
+ orElse ... 部分関数にマッチしなかった引数を次の部分関数にマッチさせる関数合成
+ runWith ... 部分関数の結果を利用する関数と合成する
+ lift ... 関数の結果をOptionで返す関数に変換する
+ applyOrElse ... 引数がマッチすればその結果を返し、マッチしなければデフォルト値を返す
+ [Usage] ふつうの関数(全域関数)の代わりに使う
+ [Usage] TraversableLike#collect
+ [Usage] TraversableOnce#collectFirst
+ [Usage] Try#collect
+ ListはPartialFunction[Int,A]をmix-inしている
+ MapはPartialFunction[K,V]をmix-inしている
