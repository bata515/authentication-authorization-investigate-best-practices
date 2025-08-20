# CLAUDE.md

このファイルは、Claude Code (claude.ai/code) がこのリポジトリで作業する際のガイダンスを提供します。

## プロジェクト概要

このプロジェクトは、Spring Boot + Google OAuth 2.0を使用したGoogleログイン機能の実装デモ環境です。認証・認可のベストプラクティスを調査・実装するためのプロジェクトです。

## アーキテクチャ

プロジェクトは標準的なSpring Bootの構造に従っています：
- **メインアプリケーション**: `demo/src/main/java/com/example/demo/DemoApplication.java` - Spring Bootのエントリーポイント（DataSource自動設定は除外済み）
- **テスト構造**: `demo/src/test/java/com/example/demo/` - JUnit 5テストスイート
- **リソース**: `demo/src/main/resources/` - 設定ファイルと静的リソース

### 主要技術スタック
- Spring Boot 3.5.4 (Java 17)
- Spring Security（認証・認可機能）
- Google OAuth 2.0（Googleログイン機能）
- Spring Data JPA（現在DataSourceAutoConfiguration除外により無効化）
- PostgreSQL データベースドライバー（設定済みだが非アクティブ）
- Maven（ビルド管理）

## 開発コマンド

すべてのコマンドは `demo/` ディレクトリから実行してください：

### ビルドと実行
```bash
# アプリケーションのビルド
./mvnw clean compile

# アプリケーションの実行
./mvnw spring-boot:run

# アプリケーションのパッケージ化
./mvnw clean package
```

### テスト
```bash
# 全テストの実行
./mvnw test

# 特定のテストクラスの実行
./mvnw test -Dtest=DemoApplicationTests

# カバレッジ付きテストの実行
./mvnw test jacoco:report
```

### 開発ワークフロー
```bash
# クリーンビルドとインストール
./mvnw clean install

# 開発モードでの実行（自動再起動有効）
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev"
```

## 設定に関する注意事項

- `DemoApplication.java:7` でデータベース自動設定が除外されています
- PostgreSQL依存関係は含まれていますが、アクティブに設定されていません
- `application.properties` でアプリケーション名が "demo" に設定されています
- Spring Security starterによりセキュリティ機能が含まれていますが、まだ実装されていません

## プロジェクトの目的

このリポジトリは、Spring SecurityとGoogle OAuth 2.0を使用したGoogleログイン機能の実装と、認証・認可のベストプラクティスの調査・実験を目的としています。現在の設定は最小限であり、セキュリティパターンの探索と実験を意図しています。