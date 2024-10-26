use test;

CREATE TABLE TBL_USER (
  ID VARCHAR(100) NOT NULL COMMENT 'ユーザID',
  NAME VARCHAR(64) NOT NULL COMMENT 'ユーザ名',
  AGE INT NOT NULL COMMENT '年齢',
  EMAIL_ADDRESS VARCHAR(255) NOT NULL COMMENT 'メールアドレス',
  CREATED_BY VARCHAR(100) NOT NULL COMMENT '作成者',
  CREATED_AT DATETIME NOT NULL COMMENT '作成日時' DEFAULT CURRENT_TIMESTAMP,
  UPDATED_BY VARCHAR(100) COMMENT '更新者',
  UPDATED_AT DATETIME COMMENT '更新日時' DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(ID)
) ENGINE = InnoDB, COMMENT 'ユーザ情報管理テーブル';