DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE category
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE account
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    oauth_id           VARCHAR UNIQUE NOT NULL,
    email              VARCHAR(254)   NOT NULL,
    profile_image_path VARCHAR        NOT NULL,
    nickname           VARCHAR(20)    NOT NULL,
    phone_number       VARCHAR(15)    NOT NULL,
    grade              VARCHAR        NOT NULL DEFAULT 'VIP',
    si_do              VARCHAR(14),
    si_gun_gu          VARCHAR(10),
    eup_myeon_dong     VARCHAR(10),
    create_date        TIMESTAMP               DEFAULT NOW(),
    update_date        TIMESTAMP
);

CREATE TABLE block
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    applicant_id BIGINT,
    block_id     BIGINT,
    CONSTRAINT fk_applicant_account FOREIGN KEY (applicant_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_block_account FOREIGN KEY (block_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE post
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    author_id       BIGINT       NOT NULL,
    title           VARCHAR(500) NOT NULL,
    content         TEXT         NOT NULL,
    category_id     INTEGER,
    hit             INTEGER      NOT NULL DEFAULT 0,
    expiration_date VARCHAR,
    create_date     TIMESTAMP    NOT NULL DEFAULT NOW(),
    update_date     TIMESTAMP,
    taker_id        BIGINT,
    deal_status     VARCHAR(10)  NOT NULL DEFAULT 'YET',
    last_deal_date  TIMESTAMP,
    post_type       VARCHAR(10),
    CONSTRAINT post_writer_fkey FOREIGN KEY (author_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT post_category_fkey FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT post_taker_fkey FOREIGN KEY (taker_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE post_image
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    post_id         BIGINT  NOT NULL,
    post_image_path VARCHAR NOT NULL,
    CONSTRAINT image_post_fkey FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE review
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    post_id     BIGINT    NOT NULL,
    content     TEXT      NOT NULL,
    star_rating INTEGER   NOT NULL,
    create_date TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT review_post_fkey FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE likes
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id     BIGINT    NOT NULL,
    post_id        BIGINT    NOT NULL,
    create_date    TIMESTAMP NOT NULL DEFAULT NOW(),
    current_status BOOLEAN            DEFAULT FALSE,
    CONSTRAINT likes_account_fkey FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT likes_post_fkey FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE chat
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    room_title VARCHAR(50),
    post_id    BIGINT,
    chat_type  VARCHAR(10),
    CONSTRAINT chat_post_fkey FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE message
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    chat_id     BIGINT    NOT NULL,
    message     text      NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT message_chat_fkey FOREIGN KEY (chat_id) REFERENCES chat (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE account_has_chat
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id BIGINT NOT NULL,
    chat_id    BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (chat_id) REFERENCES chat (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE follow
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    following_id BIGINT NOT NULL,
    follower_id  BIGINT NOT NULL,
    CONSTRAINT follow_following_fkey FOREIGN KEY (following_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT follow_follower_fkey FOREIGN KEY (follower_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE
);