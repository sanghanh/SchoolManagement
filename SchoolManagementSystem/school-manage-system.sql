USE [master]
GO
/****** Object:  Database [SchoolManagementSystem]    Script Date: 8/6/2023 8:47:17 PM ******/
CREATE DATABASE [SchoolManagementSystem]
GO
USE [SchoolManagementSystem]
GO
/****** Object:  Table [dbo].[Classroom]    Script Date: 8/6/2023 8:47:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Classroom](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[TeacherId] [int] NOT NULL,
 CONSTRAINT [PK_Classroom] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 8/6/2023 8:47:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Description] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enrollments]    Script Date: 8/6/2023 8:47:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrollments](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[StudentID] [int] NOT NULL,
	[CourseId] [int] NOT NULL,
	[ClassroomId] [int] NOT NULL,
	[Score] [float] NULL,
 CONSTRAINT [PK_Enrollments] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 8/6/2023 8:47:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Phone] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teacher]    Script Date: 8/6/2023 8:47:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teacher](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Phone] [varchar](20) NOT NULL,
	[Password] [varchar](50) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Classroom] ON 

INSERT [dbo].[Classroom] ([Id], [Name], [TeacherId]) VALUES (1, N'L-256', 1)
INSERT [dbo].[Classroom] ([Id], [Name], [TeacherId]) VALUES (2, N'L-154', 2)
INSERT [dbo].[Classroom] ([Id], [Name], [TeacherId]) VALUES (3, N'L-345', 3)
INSERT [dbo].[Classroom] ([Id], [Name], [TeacherId]) VALUES (4, N'L-026', 4)
INSERT [dbo].[Classroom] ([Id], [Name], [TeacherId]) VALUES (5, N'L-412', 5)
SET IDENTITY_INSERT [dbo].[Classroom] OFF
GO
SET IDENTITY_INSERT [dbo].[Course] ON 

INSERT [dbo].[Course] ([Id], [Name], [Description]) VALUES (1, N'WE', N'Writing Essay')
INSERT [dbo].[Course] ([Id], [Name], [Description]) VALUES (2, N'JW', N'Java Web')
INSERT [dbo].[Course] ([Id], [Name], [Description]) VALUES (3, N'NET', N'C#')
INSERT [dbo].[Course] ([Id], [Name], [Description]) VALUES (4, N'IOT', N'Internet Of Things')
INSERT [dbo].[Course] ([Id], [Name], [Description]) VALUES (5, N'UE', N'Unity engine')
SET IDENTITY_INSERT [dbo].[Course] OFF
GO
SET IDENTITY_INSERT [dbo].[Enrollments] ON 

INSERT [dbo].[Enrollments] ([Id], [StudentID], [CourseId], [ClassroomId], [Score]) VALUES (1, 1, 2, 2, 1)
INSERT [dbo].[Enrollments] ([Id], [StudentID], [CourseId], [ClassroomId], [Score]) VALUES (2, 2, 1, 2, 2)
INSERT [dbo].[Enrollments] ([Id], [StudentID], [CourseId], [ClassroomId], [Score]) VALUES (3, 1, 2, 1, 1)
SET IDENTITY_INSERT [dbo].[Enrollments] OFF
GO
SET IDENTITY_INSERT [dbo].[Student] ON 

INSERT [dbo].[Student] ([Id], [FirstName], [LastName], [Email], [Phone]) VALUES (1, N'Đình', N'Thái Bảo', N'thaibao@gmail,com', N'1234567890')
INSERT [dbo].[Student] ([Id], [FirstName], [LastName], [Email], [Phone]) VALUES (2, N'Nguyễn', N'Văn A', N'nguyenvana@gmail.com', N'1234567890')
INSERT [dbo].[Student] ([Id], [FirstName], [LastName], [Email], [Phone]) VALUES (3, N'Nguyễn', N'Văn B', N'nguyenvanb@gmail.com', N'1234567890')
INSERT [dbo].[Student] ([Id], [FirstName], [LastName], [Email], [Phone]) VALUES (4, N'Nguyễn', N'Văn C', N'nguyenvanc@gmail.com', N'1234567890')
INSERT [dbo].[Student] ([Id], [FirstName], [LastName], [Email], [Phone]) VALUES (5, N'Nguyễn', N'Văn D', N'nguyenvand@gmail.com', N'1234567890')
SET IDENTITY_INSERT [dbo].[Student] OFF
GO
SET IDENTITY_INSERT [dbo].[Teacher] ON 

INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [Email], [Phone], [Password]) VALUES (1, N'Nguyễn', N'Hoàng', N'nguyenhoang@gmail.com', N'1234567890', N'123')
INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [Email], [Phone], [Password]) VALUES (2, N'Nguyễn', N'Phát', N'nguyenphat@gmail.com', N'1234567890', N'123')
INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [Email], [Phone], [Password]) VALUES (3, N'Nguyễn', N'Tài', N'nguyentai@gmail.com', N'1234567890', N'123')
INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [Email], [Phone], [Password]) VALUES (4, N'Nguyễn', N'Đạt', N'nguyendat@gmail.com', N'1234567890', N'123')
INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [Email], [Phone], [Password]) VALUES (5, N'Nguyễn', N'Bảo', N'nguyenbao@gmail.com', N'1234567890', N'123')
SET IDENTITY_INSERT [dbo].[Teacher] OFF
GO
ALTER TABLE [dbo].[Classroom]  WITH CHECK ADD  CONSTRAINT [FK_Classroom_Teacher] FOREIGN KEY([TeacherId])
REFERENCES [dbo].[Teacher] ([Id])
GO
ALTER TABLE [dbo].[Classroom] CHECK CONSTRAINT [FK_Classroom_Teacher]
GO
ALTER TABLE [dbo].[Enrollments]  WITH CHECK ADD  CONSTRAINT [FK_Enrollments_Classroom] FOREIGN KEY([ClassroomId])
REFERENCES [dbo].[Classroom] ([Id])
GO
ALTER TABLE [dbo].[Enrollments] CHECK CONSTRAINT [FK_Enrollments_Classroom]
GO
ALTER TABLE [dbo].[Enrollments]  WITH CHECK ADD  CONSTRAINT [FK_Enrollments_Course] FOREIGN KEY([CourseId])
REFERENCES [dbo].[Course] ([Id])
GO
ALTER TABLE [dbo].[Enrollments] CHECK CONSTRAINT [FK_Enrollments_Course]
GO
ALTER TABLE [dbo].[Enrollments]  WITH CHECK ADD  CONSTRAINT [FK_Enrollments_Student] FOREIGN KEY([StudentID])
REFERENCES [dbo].[Student] ([Id])
GO
ALTER TABLE [dbo].[Enrollments] CHECK CONSTRAINT [FK_Enrollments_Student]
GO
USE [master]
GO
ALTER DATABASE [SchoolManagementSystem] SET  READ_WRITE 
GO
