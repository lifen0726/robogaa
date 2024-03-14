
﻿
-- 檢查是否存在名為 Team1DB 的資料庫，如果不存在則建立
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'Team1DB_test')
    CREATE DATABASE Team1DB_test;
GO
--	使用 Team1DB 資料庫
USE Team1DB_test;


--	會員功能

----	檢查是否存在名為 members 的資料表，如果不存在則建立
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'members')
    CREATE TABLE members (
        memberid INT IDENTITY(1,1) PRIMARY KEY,
        username VARCHAR(50) UNIQUE NOT NULL,--登入帳號，唯一性
        password VARCHAR(500) NOT NULL,--登入密碼
        nickname VARCHAR(50) NOT NULL,--會員名稱，可以重複
        admin BIT DEFAULT 0,--是否為管理員，預設為false
		deleted BIT DEFAULT 0--是否標記刪除，預設為false
    );

---- 檢查 members 資料表是否為空，如果為空則插入三筆測試數據（密碼為經過BCrypt雜湊的＂123456＂）
IF NOT EXISTS (SELECT TOP 1 * FROM members)
    INSERT INTO members (username, password, nickname, admin) VALUES 
	('admin', '$2a$10$.UAoeaAVeH8vhPsxHaw1I.teyo3iBunZllqraM1EmHQJwk1CkwD8u', 'Master', 1),
	('user1', '$2a$10$.UAoeaAVeH8vhPsxHaw1I.teyo3iBunZllqraM1EmHQJwk1CkwD8u', 'Mary', 0),
	('user2', '$2a$10$.UAoeaAVeH8vhPsxHaw1I.teyo3iBunZllqraM1EmHQJwk1CkwD8u', 'Jack', 0);


SELECT * FROM members






USE [Team1DB_test]
GO

/****** Object:  Table [dbo].[Trails]    Script Date: 3/13/2024 10:26:50 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Trails](
	[tid] [int] IDENTITY(1,1) NOT NULL,
	[tname] [nvarchar](255) NULL,
	[tphoto] [varbinary](max) NULL,
	[tphotobase64] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[tid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


---- 如果Trails為空則插入三筆測試數據
IF NOT EXISTS (SELECT TOP 1 * FROM Trails)
-- Inserting data into the Trails table
INSERT INTO Trails (tname, tphoto, tphotobase64) 
VALUES ('Trail 1', NULL, NULL),
       ('Trail 2', NULL, NULL),
       ('Trail 3', NULL, NULL);




---create table TrailPhotos

USE [Team1DB_test]
GO

/****** Object:  Table [dbo].[TrailPhotos]    Script Date: 3/13/2024 10:28:40 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TrailPhotos](
	[pid] [int] IDENTITY(1,1) NOT NULL,
	[tid] [int] NULL,
	[photo] [varbinary](max) NULL,
	[pname] [nvarchar](255) NULL,
	[base64] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[TrailPhotos]  WITH CHECK ADD FOREIGN KEY([tid])
REFERENCES [dbo].[Trails] ([tid])
GO


---- 如果為空則插入三筆測試數據
IF NOT EXISTS (SELECT TOP 1 * FROM TrailPhotos)
-- Inserting data into the TrailPhotos table
INSERT INTO TrailPhotos (tid, photo, pname, base64) 
VALUES (1, NULL, 'Photo 1', NULL),
	   (1, NULL, 'Photo 2', NULL),
       (2, NULL, 'Photo 2', NULL),
       (3, NULL, 'Photo 3', NULL);


---------create table likes


USE [Team1DB_test]
GO

/****** Object:  Table [dbo].[likes]    Script Date: 3/13/2024 10:29:14 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[likes](
	[mid] [int] NULL,
	[tid] [int] NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[likes]  WITH CHECK ADD  CONSTRAINT [FK__likes__mid__68487DD7] FOREIGN KEY([mid])
REFERENCES [dbo].[members] ([memberid])
GO

ALTER TABLE [dbo].[likes] CHECK CONSTRAINT [FK__likes__mid__68487DD7]
GO

ALTER TABLE [dbo].[likes]  WITH CHECK ADD FOREIGN KEY([tid])
REFERENCES [dbo].[Trails] ([tid])
GO



----如果為空則插入三筆測試數據
IF NOT EXISTS (SELECT TOP 1 * FROM likes)
-- Inserting data into the likes table
-- Let's say member with memberid = 1 likes trails with tid = 1 and tid = 2
INSERT INTO likes (mid, tid)
VALUES (1, 1),
       (1, 2);


select * from trails
select * from members
select * from likes
select * from TrailPhotos
SELECT m.username, t.tname
FROM members m
INNER JOIN likes l ON m.memberid = l.mid
INNER JOIN Trails t ON l.tid = t.tid;

SELECT m.username, t.tname
FROM members m
LEFT JOIN likes l ON m.memberid = l.mid
LEFT JOIN Trails t ON l.tid = t.tid;


SELECT t.tid, t.tname, tp.pid, tp.pname
FROM Trails t
INNER JOIN TrailPhotos tp ON t.tid = tp.tid;




-------------------------------------------------------



CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](100) NULL,
	[Description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

-- 插入分類
INSERT INTO [Team1DB_test].[dbo].[Categories] ([CategoryName], [Description])
VALUES ('新手入門板', '專為那些剛開始踏足登山世界的朋友而設的板塊。在這裡，你可以提出任何有關初次登山的問題，尋求建議、分享經驗，並與其他新手一起成長。我們的社區成員將竭誠回答你的疑問，幫助你順利踏上登山之旅。');

INSERT INTO [Team1DB_test].[dbo].[Categories] ([CategoryName], [Description])
VALUES ('裝備討論板', '在裝備討論板，你可以盡情分享你所擁有的裝備，包括帳篷、睡袋、登山靴、登山錘等等。你也可以詢問其他會員對於特定裝備的評價、使用心得，或是提出選購裝備時的疑問和建議。');

INSERT INTO [Team1DB_test].[dbo].[Categories] ([CategoryName], [Description])
VALUES ('攝影與紀錄板', '在攝影與紀錄板，你可以發布你在登山過程中拍攝的精彩照片、影片，並與其他會員分享你的登山經歷和感受。無論是壯麗的山川風景、雄偉的高山景觀還是難忘的登山趣事，都歡迎你在這裡與大家一同分享。');

INSERT INTO [Team1DB_test].[dbo].[Categories] ([CategoryName], [Description])
VALUES ('交流與結伴板', '在交流與結伴板，你可以發布自己的登山計畫，尋找同路人一同踏上登山旅程，或是組隊挑戰更高的山峰。無論你是想尋找夥伴一同探索陌生的山川，還是想參加其他會員組織的登山活動，這裡都是你找到志同道合的伙伴的地方。');



CREATE TABLE [dbo].[threads](
	[threadID] [int] IDENTITY(1,1) NOT NULL,
	[categoryID] [int] NULL,
	[memberID] [int] NULL,
	[title] [varchar](255) NULL,
	[content] [text] NULL,
	[createdate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[threadID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[threads]  WITH CHECK ADD  CONSTRAINT [FK_categoryID] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[threads] CHECK CONSTRAINT [FK_categoryID]
GO

ALTER TABLE [dbo].[threads]  WITH CHECK ADD  CONSTRAINT [FK_memberID] FOREIGN KEY([memberID])
REFERENCES [dbo].[Members] ([MemberID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[threads] CHECK CONSTRAINT [FK_memberID]
GO

-- 插入文章
INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (1, 1, '登山新手入門攻略｜合歡山、玉山，5大初階百岳路線推薦、登山裝備，登山新手也可以挑戰百岳！', '近年來，登山、百岳在台灣掀起了一股風潮，很多人都說一旦入坑登山這圈子，就會無法自拔，甚至會愈來愈深的中毒成癮，花錢購入登山裝備、一次次挑戰不同的山群。登山新手要怎麼登上百岳？要做哪些功課能？這篇登山新手攻略，幫大家從登山裝備到初階百岳路線規劃，通通幫你整理好，只要事前準備做足，新手也可以挑戰百岳！ 登山新手入門必備4步驟 Step 1 選一條適合自己的路線 Step 2 準備好需要的裝備 Step 3 平常多運動，從簡單健行步道開始訓練體力 Step 4 新手請找好結伴的山友，或是跟登山團！', GETDATE());


INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (1, 2, '#分享 登山新手看這裡：必備裝備篇', '雖然這篇是寫給新手，但不代表是只有新手需要這些東西～只是老手已經登山很多次，知道什麼裝備是適合自己的，那就依照自己的習慣與喜好去準備就好～❤️❤️🙌

以下就用我自己的經驗跟大家分享五個我覺得新手可以準備或是入手的裝備

1️⃣夠大的水壺
登山最重要的就是補充水份！水不夠，登山真的很痛苦，每次登山都買寶特瓶的話又很不環保。建議可以買個 1000ml 的水壺，無毒材質就好。新手我覺得也不用買到水袋，畢竟未來沒有使用的話就是丟在那，也無法二手賣。買大水壺，即使沒有真的入登山坑，運動或是日常生活其實也都可以用。（我的水壺+登山的人都知道的店！ 2️⃣好穿的登山鞋
大背包、帳篷、睡袋等，還能用借的，登山鞋幾乎是沒辦法用借的。所以就自己買一雙吧～新手對自己的腳踝控制可能不是那麼好，所以還是建議中高筒以上。牌子的話我自己是穿相對便宜的 MERRELL ，如果想要更便宜可以看看迪卡儂，我身邊也蠻多人穿。', GETDATE());

INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (2, 1, '單攻玉山 裝備篇(和一點點體訓...', '交通篇和申請篇內容比較多，分別在之前分享過了，今天來講體訓和裝備。

我的體訓呀~(可能無參考價值XD，可跳過)

單攻玉山裝備大約4~5kg以下，個人覺得不太需要負重要求，不過腿部肌耐力要夠，畢竟要一天內走23~25km山路，含休息大約12~14小時。
兩個月前開始每星期跑步一趟5km，目標是30min跑完，但我大約是30~33min跑完，半年前還有在爬山，這次打算吃老本。


我的裝備們~

頭巾三條：頭部和頸部保暖，1~3條(有人說3條太多...他都帶1條...3條是個人習慣，留一條備用，這樣第二天還會有一條香香的頭巾~)。頭部保暖必備，基本上就是帶整天，以防吹風頭痛高山症。頸部的用來調節溫度，熱時拿下，冷就戴上，還可充當口罩，這次忘記帶手套，把備用頭巾繞在手腕上，包覆掌根當下山時的支撐點。(補充：爬玉山無論天氣好壞，手套都是必備!不可以像我一樣忘記了。)

內層：短袖排汗衣，大學系服、社團、活動累積大量排汗衣，通常我會挑黑色或紅色上山，不為甚麼，就好搭衣服，這次是黑色。下著是台中第三市場泳裝店買的噁心褲(內搭褲)，透氣快乾，還有一點緊身加壓效果，現在可能已經停賣了，迪卡儂應該有很多類似的東西，台灣爬山不用買到最厚的那款。以前都是單穿噁心褲，首次嘗試外搭Lativ厚螺紋短裙，遮屁股，修身效果不錯，意外不容易臭的材質，之後列為標配。

中層：一薄一厚，一件紅襯衫，一件迪卡儂羽絨衣。如果你去爬文，紅衫通常不算中層，台灣登山社獨有配備，補足手臂防曬防風，胸前有小口袋放紙筆和護唇膏，不用再背個Hanchor的小包，缺點透氣差，不過冬天還好，適合11月的玉山單攻，行走時調整速度和背包，不讓過多汗水浸透背部就可以，因為是穿習慣的衣服，紅色搭配起來最得心應手。迪卡儂羽絨衣，蓬鬆度660 Forclaz 夠暖，上一季出清特價時撿到，晚上裡面穿短袖也可以出門觀星。(補充：爬文會看到中層要刷毛、PS衣之類的，我的經驗裡是在台灣不常穿，所以如果想省錢，北部的人找一件衣櫃裡覺得最暖的那件問爬山的朋友可不可以，通常都是可以，沒有羽絨衣之前我帶過羊毛衣，也很暖、很輕，就是體積大了點)

外層：Mont-bell 雨衣，紅色好看，跟學姐買的二手衣，搭配新羽絨衣有點小件，今年初噴過一次防水，台中少下雨，效果還行，玉山最後1公里時，天氣說變就變，霧雨從山頭翻下來，上衣四件全用上，山頭照看起來很繃，防水膠條也開始剝落了，想更新大一點的風雨衣了。迪卡儂雨褲，黑色百搭，全程天氣好沒登場機會，不過還是介紹一下，我很喜歡它腰部的工法，兩個魔鬼粘調節很合身，走路不容易滑掉，比大潤發牌的鬆緊帶好。

鞋：Mammut Trovat High GTX Women 
硬底登山鞋，咖啡色，秋天的感覺💛平常鞋子穿24.5，登山鞋是25.5的，大一號才不會傷腳趾。羊毛厚襪，搭配登山鞋不磨腳，好襪子不容易臭，不用備用襪。這次天氣預報天氣降雨機率10~20%，沒帶綁腿。

背包：迪卡儂40L技術背包 Simond Jorasses 40 雖然大了點，不過它是我有腰帶的背包裡最小的了，聽說有可以外掛雪板或冰斧的強大功能，不過我還沒用過www。特別堅持有腰帶的，而不是一般單攻小背，才不會肩膀痠痛，背部透氣也較好，下坡時繫上腰帶背包不會晃，一整個加速開高鐵沒問題。

飲水系統：Mont-bell 750ml 保溫瓶，雖然重了點，不過山莊的飲水都是熱水，水袋和寶特瓶出局，帶耐熱的保溫水瓶比較好用，東埔山莊出發時背750ml熱水，到排雲山莊可以補給。

照明系統：登山走夜路頭燈必備，不能是手持手電筒。Petzl Tikka 頭燈，88g夠輕很好，三個AAA新電池最弱光段可用120小時，中間光段也有9小時，不太需要帶備用電池。

單攻食物：單攻午餐&行進糧小糖果&預備糧(以防萬一走到晚上)-麵包兩個，健達巧克力兩條，餅乾一包，硬糖一小包，麥片一包(排雲山莊一樓可供單攻的人進去休息，背包不能放桌椅上，可使用熱飲水) 

其他雜物：入山入園證，身分證，手機-拍攻頂照用、不帶行充開飛航或關機省電，手錶，地圖-有座標、路線、公里數、休息/廁所點，紙筆-紀錄自己的行進速度，衛生紙和夾鏈袋-任何垃圾自行帶下山，護唇膏，哨子-背包上有附，100元-很重要!下山爽搭接駁車~
雨傘-非必備，準備下雨或大太陽走林道時的爽道具


寄放在東埔山莊的東西：
內層備用衣一套，小塑膠袋，洗髮精，吸水毛巾，梳子，個人藥品，手機充電器，拖鞋。下山之後東埔山莊16:00~20:00可以洗熱水澡，不過沒有盥洗用具和吹風機，山上天氣冷，需斟酌個人狀況是否洗頭，越早洗越好，房間除濕機會開到晚餐後。

餐具，碗-山莊可訂餐，我這次前面自備，只有最後一天行程太無聊了，所以訂了早餐，80元很豐盛。單攻前一天晚餐&單攻的早餐&單攻之後的晚餐有：泡麵兩包，麵包一個，麥片包兩包，奶茶包一包。

以上，裝備講完了。都是我的個人裝備，不一定適合每個人，就是給個參考囉~
我有時下山會做檢討，檢討這次上山少帶了哪些、多帶了哪些，慢慢地不需要看清單，也能馬上打包適合每趟行程的個人裝備。
登山裝備一定花錢，但可以慢慢投資，大一暑假打工開學前買登山背包，大三暑假打工買羽絨睡帶，畢業工作後才買登山鞋...', GETDATE());

INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (3, 1, '#請益 登山相機選擇', '因為喜歡爬山，總覺得用手機拍沒辦法呈現大景的美，最近想用打工的錢買一台相機，但因為完全沒接觸過身邊也沒人懂攝影領域加上一台都要好幾萬不希望隨便盲買。
我想要輕量化體積小，新手能夠易上手的登山相機（外型希望復古🥺）預算在2萬5以內，希望板上能推薦🙇🏻‍♀️

以下有幾個問題
1.推薦微單還是類單？
（網路爬文說微單可換鏡頭好，但新手不太知道鏡頭要怎麼選⋯還是剛入門類單比較適合？）

2.微單單機身是不是不能拍照？
鏡頭一定要裝同品牌的嗎？
（抱歉我知道這是蠢問題，但我想再確認一下😓）

3.想要拍銀河星空有沒有特定規格的鏡頭/相機才能呈現？還是只是設定光圈快門的問題？

4.景深是只有微單那種手動鏡頭才能呈現嗎？

5.品牌推薦？
個人喜歡日系一點的色調🙇🏻‍♀️', GETDATE());







CREATE TABLE [dbo].[Replies](
	[ReplyID] [int] IDENTITY(1,1) NOT NULL,
	[ThreadID] [int] NOT NULL,
	[MemberID] [int] NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Replies] PRIMARY KEY CLUSTERED 
(
	[ReplyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[Replies]  WITH CHECK ADD  CONSTRAINT [FK_RepliesMemberID] FOREIGN KEY([MemberID])
REFERENCES [dbo].[Members] ([MemberID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Replies] CHECK CONSTRAINT [FK_RepliesMemberID]
GO

ALTER TABLE [dbo].[Replies]  WITH CHECK ADD  CONSTRAINT [FK_ThreadID] FOREIGN KEY([ThreadID])
REFERENCES [dbo].[threads] ([threadID])
GO

ALTER TABLE [dbo].[Replies] CHECK CONSTRAINT [FK_ThreadID]
GO


-- 插入回覆

INSERT INTO [Team1DB_test].[dbo].[Replies] ([ThreadID], [MemberID], [Content], [CreateDate] )
VALUES ( 1, 2, '這是第一筆回覆內容。', '2024-03-13 12:00:00')


INSERT INTO [Team1DB_test].[dbo].[Replies] ( [ThreadID], [MemberID], [Content], [CreateDate])
VALUES ( 1, 1, '這是第二筆回覆內容。','2024-03-13 12:15:00')


INSERT INTO [Team1DB_test].[dbo].[Replies] ( [ThreadID], [MemberID], [Content], [CreateDate])
VALUES ( 1, 3, '這是第三筆回覆內容。', '2024-03-13 12:30:00')

INSERT INTO [Team1DB_test].[dbo].[Replies] ([ThreadID], [MemberID], [Content], [CreateDate] )
VALUES ( 2, 2, '這是第一筆回覆內容。', '2024-03-13 12:00:00')


INSERT INTO [Team1DB_test].[dbo].[Replies] ( [ThreadID], [MemberID], [Content], [CreateDate])
VALUES ( 2, 1, '這是第二筆回覆內容。','2024-03-13 12:15:00')


INSERT INTO [Team1DB_test].[dbo].[Replies] ( [ThreadID], [MemberID], [Content], [CreateDate])
VALUES ( 2, 3, '這是第三筆回覆內容。', '2024-03-13 12:30:00')
