package com.thc.winterspr.service.impl;

import com.thc.winterspr.dto.NoticeDto;
import com.thc.winterspr.domain.Notice;
import com.thc.winterspr.mapper.NoticeMapper;
import com.thc.winterspr.repository.NoticeRepository;
import com.thc.winterspr.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;
    public NoticeServiceImpl(NoticeRepository noticeRepository, NoticeMapper noticeMapper){
        this.noticeRepository = noticeRepository;
        this.noticeMapper = noticeMapper;
    }

    @Override
    public NoticeDto.CreateResDto create(NoticeDto.CreateReqDto params) {
        return noticeRepository.save(params.toEntity()).toCreateResDto();
    }

    @Override
    public void update(NoticeDto.UpdateReqDto params) {
        Notice notice = noticeRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getDeleted() != null){ notice.setDeleted(params.getDeleted()); }
        if(params.getProcess() != null){ notice.setProcess(params.getProcess()); }
        if(params.getTitle() != null){ notice.setTitle(params.getTitle()); }
        if(params.getContent() != null){ notice.setContent(params.getContent()); }
        noticeRepository.save(notice);
    }

    @Override
    public void delete(NoticeDto.UpdateReqDto params) {
        params.setDeleted(true);
        update(params);
    }

    public NoticeDto.DetailResDto get(NoticeDto.DetailReqDto params) {
        return noticeMapper.detail(params);
    }

    @Override
    public NoticeDto.DetailResDto detail(NoticeDto.DetailReqDto params) {
        return get(params);
    }

    public List<NoticeDto.DetailResDto> addlist(List<NoticeDto.DetailResDto> list) {
        List<NoticeDto.DetailResDto> finalList = new ArrayList<>();
        for(NoticeDto.DetailResDto each : list) {
            finalList.add(get(NoticeDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return finalList;
    }

    @Override
    public List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params) {
        if(params.getOrderway() == null || params.getOrderway().isEmpty()) {
            params.setOrderway("desc");
        }
        if(params.getOrderby() == null || params.getOrderby().isEmpty()) {
            params.setOrderby("id");
        }

        List<NoticeDto.DetailResDto> tmpList = noticeMapper.list(params);
        return addlist(tmpList);
    }

    @Override
    public NoticeDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto params) {
        if(params.getOrderway() == null || params.getOrderway().isEmpty()) {
            params.setOrderway("desc");
        }
        if(params.getOrderby() == null || params.getOrderby().isEmpty()) {
            params.setOrderby("id");
        }

        int totalList = noticeMapper.pagedListCount(params);

        Integer perpage = params.getPerpage();
        if(perpage == null || perpage <= 0) {
            perpage = 10;
        }
        params.setPerpage(perpage);

        int totalPage = totalList / perpage;
        if(totalList % perpage != 0) {
            totalPage++;
        }

        Integer callpage = params.getCallpage();
        if(callpage == null || callpage <= 0) {
            callpage = 1;
        } else if(callpage > totalPage) {
            callpage = totalPage;
        }
        params.setCallpage(callpage);

        int offset = (callpage - 1) * perpage;
        params.setOffset(offset);

        List<NoticeDto.DetailResDto> pagedList = noticeMapper.pagedList(params);
        List<NoticeDto.DetailResDto> finalList = addlist(pagedList);

        NoticeDto.PagedListResDto returnVal =
                NoticeDto.PagedListResDto.builder()
                        .list(finalList).totalList(totalList).totalPage(totalPage).callpage(callpage).perpage(perpage)
                        .build();

        return returnVal;
    }

    @Override
    public List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto params) {
        //정렬 기준 입력 안했을때, 보완 코드
        if(params.getOrderway() == null || params.getOrderway().isEmpty()){
            params.setOrderway("desc");
        }
        //한 페이지에 몇개씩 볼지 확인할 것!!
        Integer perpage = params.getPerpage();
        if(perpage == null || perpage <= 0){
            perpage = 10;
        }
        params.setPerpage(perpage);
        return addlist(noticeMapper.scrollList(params));
    }
}