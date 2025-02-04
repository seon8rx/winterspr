package com.thc.winterspr.service;

import com.thc.winterspr.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    NoticeDto.CreateResDto create(NoticeDto.CreateReqDto params);
    void update(NoticeDto.UpdateReqDto params);
    void delete(NoticeDto.UpdateReqDto params);
    NoticeDto.DetailResDto detail(NoticeDto.DetailReqDto params);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params);
    NoticeDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto params);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto params);
}
