
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
VALUES (1, 2, '登山新手入門攻略｜合歡山、玉山 5 大初階百岳推薦！', '<p>近年來，登山、百岳在台灣掀起了一股風潮，很多人都說一旦入坑登山這圈子，就會無法自拔，甚至會愈來愈深的中毒成癮，花錢購入登山裝備、一次次挑戰不同的山群。<br><br>登山新手要怎麼登上百岳？要做哪些功課？這篇登山新手攻略，幫大家從登山裝備到初階百岳路線規劃，通通幫你整理好，只要事前準備做足，新手也可以挑戰百岳！（延伸閱讀 /疫情下「登山」正夯！6 個專有名詞，新手一定要懂）<br><br>登山新手入門必備 4 步驟<br>在開始進入正文之前，要先具備一個重要的登山觀念 – 「無痕山林」，你帶了什麼東西上山，就要把它帶下去，不要留下你的物品，請攜帶垃圾袋上山，並將你的垃圾帶走，不要對森林造成任何的傷害與負擔，才能讓環境與美景永續。<br><br>Step 1 選一條適合自己的路線<br>登山也是有分等級的，每座山的難度都不一樣，根據自己的程度判斷、選擇登山路線，真的很重要，千萬不要抱有僥倖或是越級打怪的心態，從初級入門再慢慢一座座挑戰。（延伸閱讀 /登山怎麼穿才安全、時髦？鞋、衣、配件挑選 4 守則）<br><br>畢竟山就是在那裡，以後要挑戰多的是機會！千萬不要操之過急！初階路線通常不會有過於陡峭的坡度，設計的步道也會較完善，不用太多技巧，臨時反悔或是身體不適，折返的過程也會比較簡單、容易，對新手來說安全度會更高！<br><br>建議大家可以上一些登山專門網站，或是上 youtube 看看大家分享的爬山經驗，可以參考路線的走法、難易度等，方便判斷此座百岳是否適合自己，或是在健行筆記和 Hikingbook 兩大網站中找相關登山教學與知識，這兩個網站都有推出 APP，上面有許多 GPX（山友們登山的軌跡或路程），可以先載下來，方便登山時使用、參考，另外也有離線地圖、離線 GPS 定位功能，讓登山時的安全性提升。<br><br><br>Step 2 準備好需要的裝備<br>一開始入門登山時，總是會煩惱裝備要準備什麼、怎麼買，如果身邊已經有登山經驗的朋友，那就勇敢地開口借吧！出外就是要靠朋友啊～ 真的沒朋友可以借的話，就去迪卡儂逛逛，找找 CP 值較高的登山用品，一開始可以不用買到太高級，等比較有經驗、愈來愈了解自己的需求後，再開始入挑選較高階或是高單價的商品，可以避免踩雷。<br><br>但有一樣單品是小編建議一定要入手的！就是登山鞋，畢竟別人的可能不是最適合自己的，加上舒適度、防滑度、防水度等也會影響你的攀登過程或影響到速度等，所以在一開始在選擇鞋子上可以多花一點心思。<br><br>Step 3 平常多運動，從簡單健行步道開始訓練體力<br>虎山登山步道<br>臺北市政府觀光傳播局 王能佑攝影<br>登山這件事是需要慢慢累積的，不是一下子就可以變得超強或超熟練，建議在登山之前，可以先從簡單的步道或是小山開始訓練，多走幾個步道，訓練自己的腳程、呼吸等。平常也可以跑步訓練自己的心肺功能，有多一點的餘力，也可以去重訓，加強自己的肌群，簡單來說，就是要從日常生活中多運動，累積多一點體力，上山才不會累死！<br><br><br>Step 4 新手請找好結伴的山友，或是跟登山團！<br>這點真的很重要！即使你今天是要登超級初階、挑戰性較低的山，都請你找好願意跟你一起同行的山友！尤其是零基礎的新手，更需要有登山經驗的人來陪伴你，減少危險的發生，不論是你臨時有狀況，或是遇到突發情形，都會有人可以 cover 你，心理上來說也會比較安心。<br><br>另外提醒大家，百岳的登山口通常不太好到達，如果不是自己開車，建議可以和朋友們一起搭乘接駁車，一群人一起分攤， 一個人來回才 1000 多塊 ，不用舟車勞頓，直接幫你把行李和人送至登山口，加上現在有早鳥優惠更划算！<br><br>如果要更安全、保險的話，建議直接參加登山團，因為登山團不用自己背糧食、睡袋等，會輕鬆許多，沿途也會有嚮導陪伴，確認你的身體狀況、腳程等。</p>', GETDATE());


INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (1, 2, '#分享 登山新手看這裡：必備裝備篇', '<p>雖然這篇是寫給新手，但不代表是只有新手需要這些東西～只是老手已經登山很多次，知道什麼裝備是適合自己的，那就依照自己的習慣與喜好去準備就好～<br><br>以下就用我自己的經驗跟大家分享五個我覺得新手可以準備或是入手的裝備<br><br>1.夠大的水壺<br>登山最重要的就是補充水份！水不夠，登山真的很痛苦，每次登山都買寶特瓶的話又很不環保。建議可以買個 1000ml 的水壺，無毒材質就好。新手我覺得也不用買到水袋，畢竟未來沒有使用的話就是丟在那，也無法二手賣。買大水壺，即使沒有真的入登山坑，運動或是日常生活其實也都可以用。<br>（我的水壺+登山的人都知道的店！<br><br>2.好穿的登山鞋<br>大背包、帳篷、睡袋等，還能用借的，登山鞋幾乎是沒辦法用借的。所以就自己買一雙吧～新手對自己的腳踝控制可能不是那麼好，所以還是建議中高筒以上。牌子的話我自己是穿相對便宜的 MERRELL ，如果想要更便宜可以看看迪卡儂，我身邊也蠻多人穿。<br>（怕被說?? 放一個小小的鞋子圖<br><br>3.小斜包（胸前包）<br>這個真的是我覺得超好用的一個裝備，可以裝手機、衛生紙、錢包等時常需要拿出來用的裝備，不一定要登山品牌的～好背、拉鍊好拉即可（100元-2000元 我身邊都有人在背，跟厲害程度無關，純粹就自己喜好）我之前買了一個 1000 多的，當下覺得有點貴，但幾乎每天都背，好值得！（出國當貼身包，背在外套裡也可以）<br>（放一個示意 反正就是一個小斜包??<br><br>4.登山帽<br>帽子不只是配件而已～帽子真的hen重要，防風又防曬防雨。登山時吹冷風，吹久頭真的...很痛，用帽子遮會好很多。防曬同理。防雨的部分，我自己親身經歷，有次戴不防水的帽子登山，結果下雨，整個帽子變成集水器，好崩潰。下山果斷買一頂防水材質帽～建議使用有繩子的（或是自己加個帽繩），不然帽子飛走很難過，也造成環境污染。<br>（沒有特別拍帽子特寫 我是戴圓盤帽～<br><br>5.頭巾<br>很多網美戴頭巾或是髮帶都超好看，很有特色。但我戴起來真的就是...hen好笑，不過沒關係，實用就好，頭巾可以用來擦汗、綁頭髮、蓋住登山無法洗頭油油ㄉ頭髮、還可以防曬！！！！必備！（品牌不拘，小北百貨30摳一樣可以登玉山ＸＤ）<br></p>', GETDATE());

INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (2, 1, '單攻玉山 裝備篇(和一點點體訓...', '<p>交通篇和申請篇內容比較多，分別在之前分享過了，今天來講體訓和裝備。<br><br>我的體訓呀~(可能無參考價值XD，可跳過)<br><br>單攻玉山裝備大約4~5kg以下，個人覺得不太需要負重要求，不過腿部肌耐力要夠，畢竟要一天內走23~25km山路，含休息大約12~14小時。<br>兩個月前開始每星期跑步一趟5km，目標是30min跑完，但我大約是30~33min跑完，半年前還有在爬山，這次打算吃老本。<br><br><br>我的裝備們~<br><br>頭巾三條：頭部和頸部保暖，1~3條(有人說3條太多...他都帶1條...3條是個人習慣，留一條備用，這樣第二天還會有一條香香的頭巾~)。頭部保暖必備，基本上就是帶整天，以防吹風頭痛高山症。頸部的用來調節溫度，熱時拿下，冷就戴上，還可充當口罩，這次忘記帶手套，把備用頭巾繞在手腕上，包覆掌根當下山時的支撐點。(補充：爬玉山無論天氣好壞，手套都是必備!不可以像我一樣忘記了。)<br><br>內層：短袖排汗衣，大學系服、社團、活動累積大量排汗衣，通常我會挑黑色或紅色上山，不為甚麼，就好搭衣服，這次是黑色。下著是台中第三市場泳裝店買的噁心褲(內搭褲)，透氣快乾，還有一點緊身加壓效果，現在可能已經停賣了，迪卡儂應該有很多類似的東西，台灣爬山不用買到最厚的那款。以前都是單穿噁心褲，首次嘗試外搭Lativ厚螺紋短裙，遮屁股，修身效果不錯，意外不容易臭的材質，之後列為標配。<br><br>中層：一薄一厚，一件紅襯衫，一件迪卡儂羽絨衣。如果你去爬文，紅衫通常不算中層，台灣登山社獨有配備，補足手臂防曬防風，胸前有小口袋放紙筆和護唇膏，不用再背個Hanchor的小包，缺點透氣差，不過冬天還好，適合11月的玉山單攻，行走時調整速度和背包，不讓過多汗水浸透背部就可以，因為是穿習慣的衣服，紅色搭配起來最得心應手。迪卡儂羽絨衣，蓬鬆度660 Forclaz 夠暖，上一季出清特價時撿到，晚上裡面穿短袖也可以出門觀星。(補充：爬文會看到中層要刷毛、PS衣之類的，我的經驗裡是在台灣不常穿，所以如果想省錢，北部的人找一件衣櫃裡覺得最暖的那件問爬山的朋友可不可以，通常都是可以，沒有羽絨衣之前我帶過羊毛衣，也很暖、很輕，就是體積大了點)<br><br>外層：Mont-bell 雨衣，紅色好看，跟學姐買的二手衣，搭配新羽絨衣有點小件，今年初噴過一次防水，台中少下雨，效果還行，玉山最後1公里時，天氣說變就變，霧雨從山頭翻下來，上衣四件全用上，山頭照看起來很繃，防水膠條也開始剝落了，想更新大一點的風雨衣了。迪卡儂雨褲，黑色百搭，全程天氣好沒登場機會，不過還是介紹一下，我很喜歡它腰部的工法，兩個魔鬼粘調節很合身，走路不容易滑掉，比大潤發牌的鬆緊帶好。<br><br>鞋：Mammut Trovat High GTX Women <br>硬底登山鞋，咖啡色，秋天的感覺<3平常鞋子穿24.5，登山鞋是25.5的，大一號才不會傷腳趾。羊毛厚襪，搭配登山鞋不磨腳，好襪子不容易臭，不用備用襪。這次天氣預報天氣降雨機率10~20%，沒帶綁腿。<br><br>背包：迪卡儂40L技術背包 Simond Jorasses 40 雖然大了點，不過它是我有腰帶的背包裡最小的了，聽說有可以外掛雪板或冰斧的強大功能，不過我還沒用過www。特別堅持有腰帶的，而不是一般單攻小背，才不會肩膀痠痛，背部透氣也較好，下坡時繫上腰帶背包不會晃，一整個加速開高鐵沒問題。<br><br>飲水系統：Mont-bell 750ml 保溫瓶，雖然重了點，不過山莊的飲水都是熱水，水袋和寶特瓶出局，帶耐熱的保溫水瓶比較好用，東埔山莊出發時背750ml熱水，到排雲山莊可以補給。<br><br>照明系統：登山走夜路頭燈必備，不能是手持手電筒。Petzl Tikka 頭燈，88g夠輕很好，三個AAA新電池最弱光段可用120小時，中間光段也有9小時，不太需要帶備用電池。<br><br>單攻食物：單攻午餐&行進糧小糖果&預備糧(以防萬一走到晚上)-麵包兩個，健達巧克力兩條，餅乾一包，硬糖一小包，麥片一包(排雲山莊一樓可供單攻的人進去休息，背包不能放桌椅上，可使用熱飲水) <br><br>其他雜物：入山入園證，身分證，手機-拍攻頂照用、不帶行充開飛航或關機省電，手錶，地圖-有座標、路線、公里數、休息/廁所點，紙筆-紀錄自己的行進速度，衛生紙和夾鏈袋-任何垃圾自行帶下山，護唇膏，哨子-背包上有附，100元-很重要!下山爽搭接駁車~<br>雨傘-非必備，準備下雨或大太陽走林道時的爽道具<br><br><br>寄放在東埔山莊的東西：<br>內層備用衣一套，小塑膠袋，洗髮精，吸水毛巾，梳子，個人藥品，手機充電器，拖鞋。下山之後東埔山莊16:00~20:00可以洗熱水澡，不過沒有盥洗用具和吹風機，山上天氣冷，需斟酌個人狀況是否洗頭，越早洗越好，房間除濕機會開到晚餐後。<br><br>餐具，碗-山莊可訂餐，我這次前面自備，只有最後一天行程太無聊了，所以訂了早餐，80元很豐盛。單攻前一天晚餐&單攻的早餐&單攻之後的晚餐有：泡麵兩包，麵包一個，麥片包兩包，奶茶包一包。<br><br>以上，裝備講完了。都是我的個人裝備，不一定適合每個人，就是給個參考囉~<br>我有時下山會做檢討，檢討這次上山少帶了哪些、多帶了哪些，慢慢地不需要看清單，也能馬上打包適合每趟行程的個人裝備。<br>登山裝備一定花錢，但可以慢慢投資，大一暑假打工開學前買登山背包，大三暑假打工買羽絨睡帶，畢業工作後才買登山鞋...</p>', GETDATE());

INSERT INTO [Team1DB_test].[dbo].[threads] ([categoryID], [memberID], [title], [content], [createdate])
VALUES (3, 1, '#請益 登山相機選擇', '<p>近一年來開始爬百岳後，發現原本的相機已無法滿足(RX100M4)，所以想換台，最主要是登山拍照，不太錄影，重量不要太重，金額方面機加鏡5萬以內<br><br>這陣子爬文選出了幾台但遲遲無法決定哪台好，再麻煩各位前輩給點意見~<br><br>1.FUJIFILM X-T30<br>綜觀這台不錯也輕但電池續航力、無防手震很朋友們一致認為APP很常當機等問題讓我比較猶豫，雖然會修圖但修不出富士感<br><br>2.Sony A6400<br>會考慮這台是因為sony的對焦能力一直都滿不錯，不管是攝影或拍照都很適合，爬文大部分人都是這台和 X-T30在選，這台的防水滴防塵在山上感覺滿需要<br><br>3.FUJIFILM  X-S10<br>這台則是看許多登山朋友都用這台所以也列入考慮，且有機身防抖<br><br>先謝謝各位指點！</p>', GETDATE());







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
