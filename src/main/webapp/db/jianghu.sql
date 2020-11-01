/*
 Navicat Premium Data Transfer

 Source Server         : localhost_1-8
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : jianghu

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/10/2020 17:58:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bc_item
-- ----------------------------
DROP TABLE IF EXISTS `bc_item`;
CREATE TABLE `bc_item` (
  `ID` decimal(8,0) NOT NULL COMMENT '主键',
  `ITEMNAME` varchar(20) DEFAULT NULL COMMENT '名称',
  `ITEMARR` varchar(50) DEFAULT NULL COMMENT '地址',
  `ITEMDSC` varchar(70) DEFAULT NULL COMMENT '简单描述',
  `ITEMTXT` longtext COMMENT '详细描述',
  `CREATTIME` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '修改日期',
  `SEECOUNT` decimal(8,0) DEFAULT NULL COMMENT '查看次数',
  `IMG_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_item
-- ----------------------------
BEGIN;
INSERT INTO `bc_item` VALUES (77, 'zTree', '/jiong/page/Tree/zTree/jianghu_zTree.html', '使用jQuery easyUI实现zTree相关操作，包括异步加载，右键菜单等', '<p>功能强大的树组件，这个就不多介绍了，应该都用过。</p>\r\n\r\n<p>后台实现了，拖拽排序，调整上下级，右键菜单等不常用的功能，可以参考。</p>\r\n', '2017-07-06 22:43:58', '2018-02-08 23:07:20', 24, 'ztree.jpg');
INSERT INTO `bc_item` VALUES (78, 'ECharts', '/jiong/page/echarts/echarts.html', 'ECharts的简单操作，包括ECharts3 Demo', '<p>这个没有什么好说的，需要什么样的样式去echarts的官网查就是了。</p>\r\n\r\n<p>这个的重点是取数。</p>\r\n', '2017-07-06 22:46:49', '2018-02-08 22:44:42', 8, 'echarts.jpg');
INSERT INTO `bc_item` VALUES (79, 'AngularJS', '/jiong/page/AngularJS/HelloWorld2.html', 'AngularJS学习时写的各种Demo，暂时没有具体应用', '<p>应该是比较火的技术，但是已经没有实际运用上。后期就会完善。</p>\r\n', '2017-07-06 22:48:29', '2018-02-08 22:31:04', 12, 'angularJs.jpg');
INSERT INTO `bc_item` VALUES (81, '爬虫', '/jiong/page/pcAndMail/pc.html', '通过URL获取制定内容，Excel导入导出，JavaMail的实现，EasyUI grid与分页的实现', '<p>这个实现的功能较多</p>\r\n\r\n<ol>\r\n	<li>通过URL获取页面内容，然后通过正则表达式获取指定内容</li>\r\n	<li>Excel的导入导出，包括导出的样式等</li>\r\n	<li>Javamail的实现</li>\r\n	<li>jQuery easyui的grid与分页功能的实现。</li>\r\n</ol>\r\n', '2017-07-06 22:53:17', '2018-02-08 22:48:09', 16, 'pc.jpg');
INSERT INTO `bc_item` VALUES (82, '贪吃蛇', '/jiong/game_snakeEat.do', '使用Java图形化组件实现贪吃蛇游戏的基本功能', '<p>与俄罗斯方块的功能差不多</p>\r\n', '2017-07-06 22:55:56', '2018-02-08 22:26:00', 11, 'tcs.jpg');
INSERT INTO `bc_item` VALUES (83, '俄罗斯方块', '/jiong/game_tetris.do', '使用Java图形化组件awing实现俄罗斯方块的基本功能', '<p>使用了工厂模式，逻辑性也比较强，就是需要熟悉的API太多，使用上可能较为麻烦，一般也不使用Java开发图形化界面。</p>\r\n', '2017-07-06 22:57:04', '2018-02-08 22:18:35', 80, 'elsfk.jpg');
INSERT INTO `bc_item` VALUES (84, '地图', '/jiong/page/map/helloWorld.html', '目前只能简单的定位到当前位置，暂时想不到什么需要开发的', '<p>需要开发时查看 http://lbsyun.baidu.com/jsdemo.htm</p>\r\n\r\n<p>开发过程和Echarts差不多，找到需要的复制过来就行了</p>\r\n', '2017-07-06 22:58:05', '2018-02-08 22:34:53', 26, 'dt.jpg');
INSERT INTO `bc_item` VALUES (85, 'Fileinput', '/jiong/page/upload/upLoad.html', '使用Bootstrap Fileinput实现图片的上传下载功能，包括toastr弹窗提示', '<p>FileInput应该是比较好用的图片上传预览控件，支持的功能也比较多，但是可能没有开发好，所以相关的代码有点烂尾。有待后期完善。</p>\r\n\r\n<p>toastr是一个弹出框，经常用做提示等，但是由于支持的功能太少，所以建议使用layer，功能强大，使用简答。</p>\r\n', '2017-07-06 23:04:16', '2018-02-08 22:36:21', 102, 'fileinput.jpg');
INSERT INTO `bc_item` VALUES (86, 'ocupload', '/jiong/page/upload/ocUpload.html', 'ocupload一键上传功能实现', '<p>一键上传，插件较为简单，点击即上传。</p>\r\n\r\n<p>原理：在页面上生成一个表单，点击后自动提交表单。</p>\r\n', '2017-07-06 23:05:01', '2018-02-08 22:53:38', 6, 'ocupload.jpg');
INSERT INTO `bc_item` VALUES (87, '序列管理', '/jiong/page/basic/sequence.html', 'Jquery EasyUI 实现序列管理的显示功能', '<p>最简单的序列管理功能，简单实验一下</p>\r\n', '2017-07-06 23:07:09', '2017-10-09 21:53:18', 17, NULL);
INSERT INTO `bc_item` VALUES (93, 'OrgChart', '/jiong/page/Tree/OrgChart/OrgChart.html', '做组织机构图做好用的控件', '<p>个人觉得就是做组织结构最强大的控件，功能强大，使用简单，相关文档完善。</p>\r\n\r\n<p>就是有一些小bug，比如从左到右显示，图形不在显示的页面中。</p>\r\n', '2017-09-27 21:55:21', '2018-02-08 22:46:38', 10, 'orgChart.jpg');
INSERT INTO `bc_item` VALUES (98, 'Lucene', '/jiong/page/basic/lucene.html', '实现了索引的CURD，并且在定时器中更新索引', '<p>实现了lucene的简单功能，包括CURD，以及手动更新（单线程）的功能，自动更新（定时器）的功能。</p>\r\n', '2017-09-30 23:48:53', '2018-02-08 22:55:23', 38, 'lucene.jpg');
INSERT INTO `bc_item` VALUES (109, '天气预报', '/jiong/page/webServices/weather.html', '通过webServices接口获取未来五天的天气，并使用layui的grid进行展示', '<p>1、layui的grid功能也较为强大，但是目前使用中，无法做到grid的宽度自适应。</p>\r\n\r\n<p>2、通过webServices接口获取未来五天的天气，并使用了DOM对返回的XML进行了解析。</p>\r\n', '2017-10-12 23:35:13', '2018-02-08 23:09:38', 36, 'tqyb.jpg');
INSERT INTO `bc_item` VALUES (110, '注册', '/jiong/page/basic/register.html', '使用layui的表单与阿里的短信服务完成用户注册功能的开发（可以获取手机归属地）', '<p>1、layui的表单相关验证封装的比较好，UI也比较好看，看了文档功能也比较强大，但是使用起来总是不顺手，在一些细节上的处理不够好，遇到的问题的比较多。</p>\r\n\r\n<p>2、阿里的短信服务封装的比较好，也有现成的demo，费用上也比较便宜。</p>\r\n\r\n<p>3、button标签增加点击事件后，点击是会刷新页面（有点坑），需要增加属性 type=&quot;button&quot;就不会刷新页面。</p>\r\n\r\n<p>4、增加了获取手机归属地的功能，使用了webxml的接口开发完成。</p>\r\n', '2017-10-17 00:08:01', '2018-02-08 22:28:41', 17, 'zc.jpg');
INSERT INTO `bc_item` VALUES (111, '右键菜单', '/jiong/page/function/rightMenu.html', '抄袭网上右键菜单，以便下次使用', '<p>&nbsp;oncontextmenu=&quot;return false&quot; &nbsp;屏蔽原来的右键菜单<br />\r\n&nbsp;onmousedown &nbsp;在鼠标按钮在元素上按下时触发。<br />\r\n&nbsp;event.button == 2 右键 &nbsp;0 左键 &nbsp;1 滑轮按下。</p>\r\n', '2017-10-18 22:37:50', '2018-02-08 23:06:09', 8, 'yj.jpg');
INSERT INTO `bc_item` VALUES (112, '获取IP、位置', '/jiong/page/function/ipOrLocation.html', 'JavaScript获取客户端的IP或者地址位置', '<p>一共有三个方法，各有优缺点，方法一是最完美的，建议使用方法一。</p>\r\n', '2017-10-21 11:56:59', '2018-02-08 23:05:53', 20, 'IP.jpg');
INSERT INTO `bc_item` VALUES (113, 'JavaScript获取宽高', '', 'js获取屏幕、网页、分辨率等操作', '<p><strong>网页可见区域宽： document.body.clientWidth&nbsp;<br />\r\n网页可见区域高： document.body.clientHeight&nbsp;</strong><br />\r\n网页可见区域宽： document.body.offsetWidth (包括边线的宽)&nbsp;<br />\r\n网页可见区域高： document.body.offsetHeight (包括边线的高)&nbsp;<br />\r\n网页正文全文宽： document.body.scrollWidth&nbsp;<br />\r\n网页正文全文高： document.body.scrollHeight&nbsp;<br />\r\n网页被卷去的高： document.body.scrollTop&nbsp;<br />\r\n网页被卷去的左： document.body.scrollLeft&nbsp;<br />\r\n网页正文部分上： window.screenTop&nbsp;<br />\r\n网页正文部分左： window.screenLeft&nbsp;<br />\r\n屏幕分辨率的高： window.screen.height&nbsp;<br />\r\n屏幕分辨率的宽： window.screen.width&nbsp;<br />\r\n屏幕可用工作区高度： window.screen.availHeight&nbsp;<br />\r\n屏幕可用工作区宽度： window.screen.availWidth</p>\r\n', '2017-10-21 23:37:37', '2018-02-08 22:50:24', 26, 'js.jpg');
INSERT INTO `bc_item` VALUES (119, 'Task', '/jiong/page/task/task_list.jsp', '记录任务相关信息以及完成情况', '<p>1、再次使用layui的表单，的确不太好用，很多常用的均没有实现。</p>\r\n\r\n<p>2、jquery easyUI还是很强大，但是前台展示不如layui好看。</p>\r\n', '2018-04-02 22:58:48', '2018-04-02 22:58:48', 1, 'task.jpg');
INSERT INTO `bc_item` VALUES (120, '图片缩放', '/jiong/page/imagerZoom/index.jsp', '图片在鼠标焦点位置通过滑轮缩放', '<p>前台Zoom缩放</p>\r\n', '2020-08-14 11:02:38', '2020-08-14 11:04:26', 9, 'zoom.jpg');
COMMIT;

-- ----------------------------
-- Table structure for bc_sequence
-- ----------------------------
DROP TABLE IF EXISTS `bc_sequence`;
CREATE TABLE `bc_sequence` (
  `SEQ_NAM` varchar(30) NOT NULL COMMENT '序列名称',
  `CURR_VAL` decimal(8,0) NOT NULL COMMENT '当前值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_sequence
-- ----------------------------
BEGIN;
INSERT INTO `bc_sequence` VALUES ('BC_USER', 72);
INSERT INTO `bc_sequence` VALUES ('BC_ITEM', 120);
INSERT INTO `bc_sequence` VALUES ('TEST_SEQ', 4);
INSERT INTO `bc_sequence` VALUES ('FRIENDMESSAGE_SEQ', 45);
COMMIT;

-- ----------------------------
-- Table structure for bc_task
-- ----------------------------
DROP TABLE IF EXISTS `bc_task`;
CREATE TABLE `bc_task` (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `begin_dtm` datetime DEFAULT NULL,
  `end_dtm` datetime DEFAULT NULL,
  `task_content` longtext,
  `task_res` varchar(3) DEFAULT NULL,
  `task_sta` varchar(3) DEFAULT NULL,
  `task_url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_task
-- ----------------------------
BEGIN;
INSERT INTO `bc_task` VALUES (2, '2018-03-29 22:59:52', NULL, '学习存储过程等数据库相关知识', NULL, NULL, '');
INSERT INTO `bc_task` VALUES (3, '2018-03-29 23:00:37', NULL, '反射', NULL, NULL, '');
INSERT INTO `bc_task` VALUES (5, '2018-03-29 23:12:30', NULL, '通过java获取外网IP1', NULL, NULL, '');
INSERT INTO `bc_task` VALUES (6, '2017-12-31 23:01:43', '2018-04-02 22:54:18', '					开发【学习笔记】管理程序	\r\n				', 'on', 'on', '/jiong/page/task/task_list.jsp');
INSERT INTO `bc_task` VALUES (8, '2018-03-29 23:15:45', NULL, '学习java23中设计模式', NULL, NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for bc_user
-- ----------------------------
DROP TABLE IF EXISTS `bc_user`;
CREATE TABLE `bc_user` (
  `ID` decimal(10,0) NOT NULL COMMENT '主键',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `INFACTNAME` varchar(255) DEFAULT NULL COMMENT '实际名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_user
-- ----------------------------
BEGIN;
INSERT INTO `bc_user` VALUES (1, 'SYS', '81dc9bdb52d04dc20036dbd8313ed055', '系统管理员');
INSERT INTO `bc_user` VALUES (2, 'TEMP', '81dc9bdb52d04dc20036dbd8313ed055', '普通用户');
INSERT INTO `bc_user` VALUES (3, '18037472122', '81dc9bdb52d04dc20036dbd8313ed055', '张三');
INSERT INTO `bc_user` VALUES (72, '18061669891', '81dc9bdb52d04dc20036dbd8313ed055', '王五');
COMMIT;

-- ----------------------------
-- Table structure for echarts
-- ----------------------------
DROP TABLE IF EXISTS `echarts`;
CREATE TABLE `echarts` (
  `NAME` varchar(10) NOT NULL,
  `SELECTED` decimal(1,0) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of echarts
-- ----------------------------
BEGIN;
INSERT INTO `echarts` VALUES ('上海', 1);
INSERT INTO `echarts` VALUES ('云南', 0);
INSERT INTO `echarts` VALUES ('内蒙古', 0);
INSERT INTO `echarts` VALUES ('北京', 0);
INSERT INTO `echarts` VALUES ('南海诸岛', 0);
INSERT INTO `echarts` VALUES ('台湾', 0);
INSERT INTO `echarts` VALUES ('吉林', 0);
INSERT INTO `echarts` VALUES ('四川', 0);
INSERT INTO `echarts` VALUES ('天津', 0);
INSERT INTO `echarts` VALUES ('宁夏', 1);
INSERT INTO `echarts` VALUES ('安徽', 0);
INSERT INTO `echarts` VALUES ('山东', 0);
INSERT INTO `echarts` VALUES ('山西', 0);
INSERT INTO `echarts` VALUES ('广东', 0);
INSERT INTO `echarts` VALUES ('广西', 0);
INSERT INTO `echarts` VALUES ('新疆', 1);
INSERT INTO `echarts` VALUES ('江苏', 1);
INSERT INTO `echarts` VALUES ('江西', 1);
INSERT INTO `echarts` VALUES ('河北', 0);
INSERT INTO `echarts` VALUES ('河南', 1);
INSERT INTO `echarts` VALUES ('浙江', 0);
INSERT INTO `echarts` VALUES ('海南', 1);
INSERT INTO `echarts` VALUES ('湖北', 0);
INSERT INTO `echarts` VALUES ('湖南', 0);
INSERT INTO `echarts` VALUES ('澳门', 0);
INSERT INTO `echarts` VALUES ('甘肃', 0);
INSERT INTO `echarts` VALUES ('福建', 0);
INSERT INTO `echarts` VALUES ('西藏', 0);
INSERT INTO `echarts` VALUES ('贵州', 1);
INSERT INTO `echarts` VALUES ('辽宁', 0);
INSERT INTO `echarts` VALUES ('重庆', 0);
INSERT INTO `echarts` VALUES ('陕西', 0);
INSERT INTO `echarts` VALUES ('青海', 0);
INSERT INTO `echarts` VALUES ('香港', 0);
INSERT INTO `echarts` VALUES ('黑龙江', 1);
COMMIT;

-- ----------------------------
-- Table structure for fc_ztree
-- ----------------------------
DROP TABLE IF EXISTS `fc_ztree`;
CREATE TABLE `fc_ztree` (
  `ID` varchar(20) NOT NULL COMMENT '主键',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `PID` varchar(20) NOT NULL COMMENT '父主键',
  `INTRO` varchar(255) DEFAULT NULL COMMENT '介绍',
  `SEQ` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fc_ztree
-- ----------------------------
BEGIN;
INSERT INTO `fc_ztree` VALUES ('1', 'src', '0', '所有的Java文件', NULL);
INSERT INTO `fc_ztree` VALUES ('101', 'com.jianghu', '1', '主目录', NULL);
INSERT INTO `fc_ztree` VALUES ('10101', 'core', '101', '核心包', NULL);
INSERT INTO `fc_ztree` VALUES ('1010101', 'Database', '10101', '数据库相关的核心类，包括获得连接，执行相关SQL语句', NULL);
INSERT INTO `fc_ztree` VALUES ('1010102', 'Tools', '10101', '系统常用工具类', NULL);
INSERT INTO `fc_ztree` VALUES ('10102', 'dao', '101', '通用Dao类', NULL);
INSERT INTO `fc_ztree` VALUES ('1010201', 'impl', '10102', '通用dao实现', NULL);
INSERT INTO `fc_ztree` VALUES ('10103', 'domain', '101', '工程中所有的类', NULL);
INSERT INTO `fc_ztree` VALUES ('1010301', 'basic', '10103', 'basic模块相关类', NULL);
INSERT INTO `fc_ztree` VALUES ('1010302', 'func', '10103', 'func模块相关类', NULL);
INSERT INTO `fc_ztree` VALUES ('1010303', 'general', '10103', '通用类', 3);
INSERT INTO `fc_ztree` VALUES ('10104', 'service', '101', '工程中所有的service', NULL);
INSERT INTO `fc_ztree` VALUES ('1010401', 'basic', '10104', 'basic模块相关service', NULL);
INSERT INTO `fc_ztree` VALUES ('101040101', 'impl', '1010401', 'basic模块service实现', NULL);
INSERT INTO `fc_ztree` VALUES ('1010402', 'func', '10104', 'func模块相关service', NULL);
INSERT INTO `fc_ztree` VALUES ('101040201', 'impl', '1010402', 'func模块相关service实现', NULL);
INSERT INTO `fc_ztree` VALUES ('10105', 'web.action', '101', 'web层所有action文件', NULL);
INSERT INTO `fc_ztree` VALUES ('2', 'config', '0', '工程中所有的配置文件', NULL);
INSERT INTO `fc_ztree` VALUES ('3', 'test', '0', '测试文件', NULL);
INSERT INTO `fc_ztree` VALUES ('4', 'jiong', '0', '所有的后台文件', NULL);
INSERT INTO `fc_ztree` VALUES ('401', 'images', '4', '所有的图片文件', NULL);
INSERT INTO `fc_ztree` VALUES ('402', 'js', '4', '所有的js文件', NULL);
INSERT INTO `fc_ztree` VALUES ('40201', 'easyUI', '402', 'JQuery EasyUI相关的js文件', NULL);
INSERT INTO `fc_ztree` VALUES ('40202', 'zTree', '402', 'zTree相关的JS文件', NULL);
INSERT INTO `fc_ztree` VALUES ('403', 'page', '4', '所有的页面文件', NULL);
INSERT INTO `fc_ztree` VALUES ('40301', 'basic', '403', 'basic模块相关的页面', 1);
INSERT INTO `fc_ztree` VALUES ('40302', 'func', '403', '功能模块相关的页面', 0);
INSERT INTO `fc_ztree` VALUES ('404', 'WEB-INF', '4', 'jar包和相关配置文件', NULL);
COMMIT;

-- ----------------------------
-- Table structure for layim_friend
-- ----------------------------
DROP TABLE IF EXISTS `layim_friend`;
CREATE TABLE `layim_friend` (
  `id` int NOT NULL AUTO_INCREMENT,
  `groupname` varchar(255) DEFAULT NULL,
  `mine_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8137A0F5B07BD4B7` (`mine_id`),
  CONSTRAINT `FK8137A0F5B07BD4B7` FOREIGN KEY (`mine_id`) REFERENCES `layim_mime` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of layim_friend
-- ----------------------------
BEGIN;
INSERT INTO `layim_friend` VALUES (1, '我的好友', 1);
INSERT INTO `layim_friend` VALUES (2, '我的同学', 1);
INSERT INTO `layim_friend` VALUES (3, '我的亲戚', 1);
INSERT INTO `layim_friend` VALUES (4, '我的好友', 2);
INSERT INTO `layim_friend` VALUES (5, '我的好友', 3);
INSERT INTO `layim_friend` VALUES (6, '我的好友', 4);
COMMIT;

-- ----------------------------
-- Table structure for layim_friendmessage
-- ----------------------------
DROP TABLE IF EXISTS `layim_friendmessage`;
CREATE TABLE `layim_friendmessage` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `fromid` int NOT NULL,
  `id` int NOT NULL,
  `mine` varchar(255) DEFAULT NULL,
  `timestamp` tinyblob,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `toid` int NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of layim_friendmessage
-- ----------------------------
BEGIN;
INSERT INTO `layim_friendmessage` VALUES (1, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123qwe且', 1, 1, 'false', 0x31353135323535333332313532, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (2, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 1, 1, 'false', 0x31353135323535373131393836, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (3, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'qwe', 1, 1, 'false', 0x31353135323535373437343532, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (4, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '请问', 1, 1, 'false', 0x31353135323536393937323938, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (5, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234', 1, 1, 'false', 0x31353135323537313133383335, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (6, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'q文档', 1, 1, 'false', 0x31353135323537343637353731, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (7, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '玩儿', 1, 1, 'false', 0x31353135323537353438323430, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (8, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '234', 1, 1, 'false', 0x31353135323537353537383733, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (9, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1233', 1, 1, 'false', 0x31353135323537363035343339, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (10, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 1, 1, 'false', 0x31353135323537373234383937, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (11, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234', 1, 1, 'false', 0x31353135323539313533373631, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (12, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'dsadf', 2, 2, 'false', 0x31353135323539313639383930, 'group', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (13, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 1, 1, 'false', 0x31353135323539323235383139, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (14, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234', 1, 1, 'false', 0x31353135323539343438363439, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (15, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234', 1, 1, 'false', 0x31353135323539353833393338, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (16, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234qw', 2, 2, 'false', 0x31353135323539353930383531, 'group', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (17, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'wqe省道', 2, 2, 'false', 0x31353135323539363232363439, 'group', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (18, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '213vv带参数', 1, 1, 'false', 0x31353135323539363533393530, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (19, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '第三方', 1, 1, 'false', 0x31353135323539363631333431, 'group', '系统管理员', 1);
INSERT INTO `layim_friendmessage` VALUES (20, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '31飞', 2, 2, 'false', 0x31353135323539363638313237, 'group', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (21, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1', 1, 1, 'false', 0x31353136313136333531303937, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (22, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 2, 2, 'false', 0x31353136313136333836313433, 'friend', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (23, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'qwe文档', 1, 1, 'false', 0x31353136313136333933373036, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (24, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234阿萨德', 1, 1, 'false', 0x31353136313136343031353834, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (25, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234a阿道夫', 1, 1, 'false', 0x31353136313136353831343234, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (26, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '11', 2, 2, 'false', 0x31353136313136353837333330, 'friend', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (27, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '12s', 1, 1, 'false', 0x31353136313136383030393635, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (28, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'asd茶山村', 1, 1, '1', 0x31353136313137303439373337, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (29, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 2, 2, '1', 0x31353136313137303535303536, 'friend', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (30, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '1234', 1, 1, '1', 0x31353136313137303738363238, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (31, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123w', 1, 1, '0', 0x31353136313137323833353936, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (32, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 2, 2, '0', 0x31353136313137323837333833, 'friend', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (33, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', 'asd', 1, 1, '0', 0x31353136313137323931393937, 'friend', '系统管理员', 2);
INSERT INTO `layim_friendmessage` VALUES (34, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123', 2, 2, '0', 0x31353136313137323934333934, 'friend', '临时工', 1);
INSERT INTO `layim_friendmessage` VALUES (35, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '123123', 1, 1, '0', 0x31353136323033393039343536, 'friend', '系统管理员', 2);
COMMIT;

-- ----------------------------
-- Table structure for layim_group
-- ----------------------------
DROP TABLE IF EXISTS `layim_group`;
CREATE TABLE `layim_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of layim_group
-- ----------------------------
BEGIN;
INSERT INTO `layim_group` VALUES (1, 'http://tp2.sinaimg.cn/2211874245/180/40050524279/0', '前端群');
INSERT INTO `layim_group` VALUES (2, 'http://tp2.sinaimg.cn/5488749285/50/5719808192/1', 'Java群');
COMMIT;

-- ----------------------------
-- Table structure for layim_mime
-- ----------------------------
DROP TABLE IF EXISTS `layim_mime`;
CREATE TABLE `layim_mime` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_id` decimal(19,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC834644B5D131311` (`user_id`),
  CONSTRAINT `FKC834644B5D131311` FOREIGN KEY (`user_id`) REFERENCES `bc_user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of layim_mime
-- ----------------------------
BEGIN;
INSERT INTO `layim_mime` VALUES (1, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '我是系统管理员', 'hide', '系统管理员', 1);
INSERT INTO `layim_mime` VALUES (2, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '我只是临时工', 'online', '临时工', 2);
INSERT INTO `layim_mime` VALUES (3, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '都叫张三，难道没有其他名字了吗', 'online', '张三', 3);
INSERT INTO `layim_mime` VALUES (4, 'http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg', '为什么我前面没有李四', 'online', '王五', 72);
COMMIT;

-- ----------------------------
-- Table structure for layim_mime_friend
-- ----------------------------
DROP TABLE IF EXISTS `layim_mime_friend`;
CREATE TABLE `layim_mime_friend` (
  `friend_id` int NOT NULL,
  `mine_id` int NOT NULL,
  KEY `FKFC14912B07BD4B7` (`mine_id`),
  KEY `FKFC149126C1A997` (`friend_id`),
  CONSTRAINT `FKFC149126C1A997` FOREIGN KEY (`friend_id`) REFERENCES `layim_friend` (`id`),
  CONSTRAINT `FKFC14912B07BD4B7` FOREIGN KEY (`mine_id`) REFERENCES `layim_mime` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of layim_mime_friend
-- ----------------------------
BEGIN;
INSERT INTO `layim_mime_friend` VALUES (1, 2);
INSERT INTO `layim_mime_friend` VALUES (2, 3);
INSERT INTO `layim_mime_friend` VALUES (3, 4);
INSERT INTO `layim_mime_friend` VALUES (4, 1);
INSERT INTO `layim_mime_friend` VALUES (4, 3);
INSERT INTO `layim_mime_friend` VALUES (4, 4);
INSERT INTO `layim_mime_friend` VALUES (5, 1);
INSERT INTO `layim_mime_friend` VALUES (5, 2);
INSERT INTO `layim_mime_friend` VALUES (5, 4);
INSERT INTO `layim_mime_friend` VALUES (6, 1);
INSERT INTO `layim_mime_friend` VALUES (6, 2);
INSERT INTO `layim_mime_friend` VALUES (6, 3);
COMMIT;

-- ----------------------------
-- Table structure for layim_mime_group
-- ----------------------------
DROP TABLE IF EXISTS `layim_mime_group`;
CREATE TABLE `layim_mime_group` (
  `mime_id` int NOT NULL,
  `group_id` int NOT NULL,
  KEY `FKE7CA198BB06DBD36` (`mime_id`),
  KEY `FKE7CA198BCE1D411D` (`group_id`),
  CONSTRAINT `FKE7CA198BB06DBD36` FOREIGN KEY (`mime_id`) REFERENCES `layim_mime` (`id`),
  CONSTRAINT `FKE7CA198BCE1D411D` FOREIGN KEY (`group_id`) REFERENCES `layim_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of layim_mime_group
-- ----------------------------
BEGIN;
INSERT INTO `layim_mime_group` VALUES (1, 1);
INSERT INTO `layim_mime_group` VALUES (2, 1);
INSERT INTO `layim_mime_group` VALUES (3, 1);
INSERT INTO `layim_mime_group` VALUES (4, 1);
INSERT INTO `layim_mime_group` VALUES (1, 2);
INSERT INTO `layim_mime_group` VALUES (3, 2);
INSERT INTO `layim_mime_group` VALUES (4, 2);
COMMIT;

-- ----------------------------
-- Table structure for mails
-- ----------------------------
DROP TABLE IF EXISTS `mails`;
CREATE TABLE `mails` (
  `ID` decimal(5,0) NOT NULL COMMENT '主键',
  `MAILS` varchar(30) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mails
-- ----------------------------
BEGIN;
INSERT INTO `mails` VALUES (1, '2222@222.com');
INSERT INTO `mails` VALUES (2, '1464986394@qq.com');
INSERT INTO `mails` VALUES (3, '1806002126@qq.com');
INSERT INTO `mails` VALUES (4, '531609263@qq.com');
INSERT INTO `mails` VALUES (5, '1981293830@qq.com');
COMMIT;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `TASK_ID` int NOT NULL,
  `TARGET_DRTN_HR_CNT` int DEFAULT NULL,
  `TARGET_START_DATE` date DEFAULT NULL,
  `TARGET_END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES (1, 5, NULL, '2018-05-29');
INSERT INTO `task` VALUES (2, 4, NULL, '2018-06-07');
INSERT INTO `task` VALUES (3, 6, NULL, '2018-05-30');
INSERT INTO `task` VALUES (4, 4, NULL, '2018-06-11');
INSERT INTO `task` VALUES (5, 6, NULL, '2018-06-12');
INSERT INTO `task` VALUES (6, 3, NULL, '2018-06-07');
INSERT INTO `task` VALUES (7, 2, NULL, '2018-06-13');
INSERT INTO `task` VALUES (8, 3, NULL, '2018-05-31');
COMMIT;

-- ----------------------------
-- Table structure for taskpred
-- ----------------------------
DROP TABLE IF EXISTS `taskpred`;
CREATE TABLE `taskpred` (
  `task_pred_id` int NOT NULL,
  `task_id` int DEFAULT NULL,
  `pred_task_id` int DEFAULT NULL,
  `pred_type` varchar(30) DEFAULT NULL,
  `lag_hr_cnt` int DEFAULT NULL,
  PRIMARY KEY (`task_pred_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taskpred
-- ----------------------------
BEGIN;
INSERT INTO `taskpred` VALUES (1, 1, 2, 'Finish to Start', 3);
INSERT INTO `taskpred` VALUES (2, 1, 3, 'Start to Start', 0);
INSERT INTO `taskpred` VALUES (3, 2, 4, 'Finish to Finish', 2);
INSERT INTO `taskpred` VALUES (4, 3, 4, 'Start to Finish', 3);
INSERT INTO `taskpred` VALUES (5, 3, 5, 'Finish to Start', 3);
INSERT INTO `taskpred` VALUES (6, 6, 7, 'Finish to Start', 2);
INSERT INTO `taskpred` VALUES (7, 8, 7, 'Start to Start', 1);
INSERT INTO `taskpred` VALUES (8, 3, 6, 'Finish to Start', 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
