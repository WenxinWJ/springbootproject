-- bank script of user  用户表
CREATE TABLE user
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    username varchar(100),
    password varchar(100),
    email varchar(100),
    enabled int,  -- 1 启用    0 禁用
    last_password_reset_date datetime,
    login_time datetime
);
-- 初始化 用户表
INSERT INTO employee.user (id, username, password, email, enabled, last_password_reset_date, login_time)
  VALUES (1, 'admin', '$2a$10$1l1spFBEYh04vL0T02yRW.I0vVUgxASN6xliG.FAVHaw7iBMMznTC', 'admin@163.com', 1, '2018-04-10 07:05:33', '2018-04-12 07:06:44');
INSERT INTO employee.user (id, username, password, email, enabled, last_password_reset_date, login_time)
  VALUES (2, 'king', '$2a$10$SjFwMTLl/f/N9jphMq62ZOCMD9PIw7xoCbMSU2Gxu2xXtkcBhQT66', 'king@163.com', 1, '2018-04-12 07:07:54', '2018-04-12 07:07:57');
INSERT INTO employee.user (id, username, password, email, enabled, last_password_reset_date, login_time)
  VALUES (3, 'smith', '$2a$10$YC7yOGx5YIHjNglYx5Y4/.0V8p0L88hL97qRrTSpcDPxTlF10pyq6', 'smith@163.com', 0, '2018-04-12 07:08:59', '2018-04-12 07:09:02');
INSERT INTO employee.user (id, username, password, email, enabled, last_password_reset_date, login_time)
  VALUES (4, 'jones', '$2a$10$sC4dtifHIR2FBXkRWbgESOxyVK0bL4/JkuaOoicy3CMVKErdVLmni', 'jones@163.com', 1, '2018-04-12 07:09:38', '2018-04-12 07:09:41');
INSERT INTO employee.user (id, username, password, email, enabled, last_password_reset_date, login_time)
  VALUES (5, 'james', '$2a$10$MpreqkuiZ5BNKEmmtC5Jx.rigxMYuQDyrwmqMGvg/GoEBXlxbpCDO', 'james@163.com', 1, '2018-04-12 07:09:57', '2018-04-12 07:10:00');

-- bank script of authority 角色表
CREATE TABLE authority
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(100),
    descn varchar(100)
);
-- 初始化 角色表
INSERT INTO employee.authority (id, name, descn) VALUES (1, 'role_admin', '系统管理员');
INSERT INTO employee.authority (id, name, descn) VALUES (2, 'role_manager', '经理');
INSERT INTO employee.authority (id, name, descn) VALUES (3, 'role_clerk', '办事员');

-- 用户-角色表   为了灵活所以没加外键约束
CREATE TABLE user_authority
(
    user_id bigint,
    authority_id bigint
);
-- 初始化 用户-角色表 (权限控制)
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (1, 3);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (3, 1);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (4, 2);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (4, 3);
INSERT INTO employee.user_authority (user_id, authority_id) VALUES (5, 3);
commit ;