# 数据库初始化
# @author ming
# @from


-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    accessKey    varchar(512)                           not null null comment 'accessKey',
    secretKey    varchar(512)                           not null null comment 'secretKey',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';


-- 接口信息
create table if not exists mingapi.`interface_info`
(
    `id`             bigint                             not null auto_increment comment '主键' primary key,
    `name`           varchar(256)                       not null comment '名称',
    `description`    varchar(256)                       null comment '描述',
    `url`            varchar(512)                       not null comment '接口地址',
    `requestParams`            varchar(512)              comment '请求参数',
    `requestHeader`  text                               null comment '请求头',
    `responseHeader` text                               null comment '响应头',
    `status`         int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
    `method`         varchar(256)                       not null comment '请求类型',
    `userId`         bigint                             not null comment '创建人',
    `createTime`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete`       tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';


insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('刘笑愚', '吴琪', 'www.wesley-trantow.com', '万鸿煊', '王越彬', 0, '黄雪松', 26826);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('潘展鹏', '叶晓啸', 'www.courtney-kassulke.net', '戴鸿煊', '袁荣轩', 0, '谢立诚', 434);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('覃天宇', '冯昊强', 'www.johnie-harris.name', '武博文', '戴思聪', 0, '毛昊焱', 2334);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('谭黎昕', '傅哲瀚', 'www.josette-adams.org', '覃振家', '吕风华', 0, '孔鹭洋', 57553);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('魏嘉熙', '沈思聪', 'www.wyatt-nader.org', '韩嘉懿', '熊思源', 0, '姚立轩', 0);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('龚修洁', '宋锦程', 'www.jerlene-grimes.io', '廖哲瀚', '张建辉', 0, '林天宇', 34618);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('蒋鑫磊', '谭明辉', 'www.micki-dicki.name', '唐雪松', '沈鹏飞', 0, '罗烨华', 27);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('钱雪松', '吕鹏', 'www.andy-russel.org', '范烨伟', '邵黎昕', 0, '苏笑愚', 5460331959);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('严晟睿', '唐晟睿', 'www.nadine-bradtke.name', '郑峻熙', '冯琪', 0, '秦烨华', 90477376);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('段浩轩', '潘文博', 'www.terrence-konopelski.co', '韩雨泽', '袁志强', 0, '陈博文', 9453614492);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('黎健雄', '龙泽洋', 'www.rodney-douglas.io', '冯晟睿', '韩明哲', 0, '蒋弘文', 70703);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('邵绍齐', '范振家', 'www.wilbur-reinger.name', '邹绍辉', '叶哲瀚', 0, '姚子轩', 24180);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('顾博文', '张瑾瑜', 'www.kittie-kautzer.name', '龙修杰', '万弘文', 0, '潘弘文', 334268466);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('唐明辉', '郝耀杰', 'www.ed-barton.org', '蒋晓啸', '段钰轩', 0, '程明', 6);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('陶泽洋', '龙语堂', 'www.shane-braun.io', '杜驰', '徐笑愚', 0, '熊展鹏', 8);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('孔炎彬', '龚昊天', 'www.rolf-wiegand.net', '赵明辉', '覃昊天', 0, '白天宇', 7453201);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('陈梓晨', '高志泽', 'www.norah-goldner.org', '何鹤轩', '郝鹏', 0, '李煜城', 791);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('白思', '汪懿轩', 'www.hugo-bradtke.co', '于立轩', '毛楷瑞', 0, '罗俊驰', 3219);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('李琪', '谭健雄', 'www.gerry-dicki.biz', '龙果', '吴晟睿', 0, '马昊焱', 61151986);
insert into `interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`,
                              `userId`)
values ('吴思', '吴志泽', 'www.kareem-feest.io', '汪黎昕', '赵瑾瑜', 0, '邱致远', 6);
