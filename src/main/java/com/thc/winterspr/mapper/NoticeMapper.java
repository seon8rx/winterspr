package com.thc.winterspr.mapper;

import com.thc.winterspr.dto.NoticeDto;

import java.util.List;

public interface NoticeMapper {

    NoticeDto.DetailResDto detail(NoticeDto.DetailReqDto param);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params);

    int pagedListCount(NoticeDto.PagedListReqDto params);
    List<NoticeDto.DetailResDto> pagedList(NoticeDto.PagedListReqDto param);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto param);
}