namespace java com.sankuai.meituan.demo.api
//分页
struct PageDTO{
    1: i32 pageNo,
    2: i32 pageSize = 25,
    /**
    * 总页数
    **/
    3: i32 totalPageCount,
    /**
    * 总条数
    **/
    4: i32 totalCount
}

//返回格式
struct ResultErrorInfoDTO{
   2: optional string code;
   3: optional string message;
}

//返回格式
struct ResultDTO{
   1: optional i32 status;
   2: optional string data;
}

//返回格式
struct ResultPageDTO{
   1: optional i32 status;
   2: optional PageDTO page;
   3: optional string data;
   4: optional string pageList;
}


service GenericService{
    ResultDTO postData(1:string params, 2:string router, 3:string format);
}