# localstack サンプル

localstackにてS3を利用開始するサンプル

## 前提

* AWS CLIをインストール

## S3を利用する

### 1. コンテナを起動

```shell
docker-compose middleware/docker-compose.yml up -d
```

### 2. バケットを作成

```shell
aws s3 mb s3://sample-bucket --endpoint http://localhost:4566
```

### 3. プログラムを実行

IDEを利用してMainクラスを実行する

### 4. 確認

```shell
aws s3 ls s3://sample-bucket --endpoint http://localhost:4566
```

### 5. 作成したリソースを削除

```shell 
aws s3 rb s3://sample-bucket --force --endpoint http://localhost:4566
```
