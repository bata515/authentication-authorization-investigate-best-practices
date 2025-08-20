# マルチステージビルドを使用してイメージサイズを最適化
FROM eclipse-temurin:17-jdk AS builder

# 作業ディレクトリを設定
WORKDIR /app

# Mavenラッパーとpom.xmlをコピー
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn

# 依存関係をダウンロード（キャッシュ効率のため）
RUN ./mvnw dependency:go-offline

# ソースコードをコピー
COPY src src

# アプリケーションをビルド
RUN ./mvnw clean package -DskipTests

# 実行用の軽量イメージ
FROM eclipse-temurin:17-jre

# 作業ディレクトリを設定
WORKDIR /app

# ビルド段階から JAR ファイルをコピー
COPY --from=builder /app/target/*.jar app.jar

# アプリケーションが使用するポートを公開
EXPOSE 8080

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "app.jar"]