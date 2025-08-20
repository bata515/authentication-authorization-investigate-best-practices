# Spring Boot OAuth2.0 Demo Application

このプロジェクトは、Spring Boot + Google OAuth 2.0を使用したGoogleログイン機能の実装デモ環境です。

## 技術スタック

- Spring Boot 3.5.4 (Java 17)
- Spring Security
- Google OAuth 2.0
- Spring Data JPA
- PostgreSQL
- Maven

## 実行方法

### 1. ローカル環境での実行

#### 前提条件
- Java 17以上
- Maven（またはMavenラッパー使用）

#### 実行手順
```bash
# demoディレクトリに移動
cd demo

# アプリケーションのビルドと実行
./mvnw spring-boot:run
```

#### その他のMavenコマンド
```bash
# ビルドのみ
./mvnw clean compile

# テスト実行
./mvnw test

# パッケージ化
./mvnw clean package
```

### 2. Dockerを使用した実行

#### 前提条件
- Docker

#### 実行手順

1. **Dockerイメージのビルド**
```bash
# demoディレクトリに移動
cd demo

# Dockerイメージをビルド
docker build -t oauth-demo .
```

2. **コンテナの実行**
```bash
# コンテナを実行（ポート8080でアクセス可能）
docker run -p 8080:8080 oauth-demo
```

3. **バックグラウンドでの実行**
```bash
# デタッチモードで実行
docker run -d -p 8080:8080 --name oauth-demo oauth-demo
```

4. **コンテナの停止と削除**
```bash
# コンテナの停止
docker stop oauth-demo

# コンテナの削除
docker rm oauth-demo
```

#### Docker Composeを使用する場合（オプション）

`docker-compose.yml`ファイルを作成して使用することも可能です：

```yaml
version: '3.8'
services:
  app:
    build: .
    container_name: oauth-demo
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
```

実行：
```bash
docker-compose up -d
```

## アクセス

アプリケーションが起動したら、ブラウザで以下にアクセスできます：
- http://localhost:8080

## 注意事項

- 現在、データベース自動設定は除外されています（`DemoApplication.java:7`）
- PostgreSQL依存関係は含まれていますが、アクティブに設定されていません
- Google OAuth 2.0の設定が必要な場合は、適切な環境変数やプロパティファイルの設定が必要です

## トラブルシューティング

- ポート8080が既に使用されている場合は、別のポートを使用してください：
  ```bash
  docker run -p 9090:8080 oauth-demo
  ```

- メモリ不足の場合は、JVMオプションを調整してください：
  ```bash
  docker run -p 8080:8080 -e JAVA_OPTS="-Xmx512m" oauth-demo
  ```