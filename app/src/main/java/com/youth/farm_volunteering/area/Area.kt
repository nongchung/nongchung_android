package com.youth.farm_volunteering.area

enum class Area(val code: Int, val regionName: String) {
    SEOUL(0, "서울"),
    PUSAN(1, "부산"),
    DAEGU(2, "대구"),
    INCHON(3, "인천"),
    GWANGJU(4, "광주"),
    DAEJEON(5, "대전"),
    ULSAN(6, "울산"),
    GYEONGGI(7, "경기"),
    GANGWON(8, "강원"),
    CHUNGNAM(9, "충남"),
    CHUNGBUK(10, "충북"),
    GYEONGNAM(11, "경남"),
    GYEONGBUK(12, "경북"),
    JEONNAM(13, "전남"),
    JEONBUK(14, "전북"),
    JEJU(15, "제주"),
    SEJONG(16, "세종"),
    ALL(17, "전국"),
}

/*
*
* 서울:0	전남:13
부산:1	전북:14
대구:2	제주:15
인천:3	세종:16
광주:4	전국:17
대전:5
울산:6

경기:7
강원:8
충남:9
충북:10
경남:11
경북:12	*/