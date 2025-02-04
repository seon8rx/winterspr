package com.thc.winterspr.domain;

import com.thc.winterspr.dto.NoticeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Notice extends AuditingFields{
    @Setter String title;
    @Setter String content;

    protected Notice(){}
    private Notice(Boolean deleted, String process, String title, String content) {
        this.deleted = deleted;
        this.process = process;
        this.title = title;
        this.content = content;
    }
    public static Notice of(String title, String content) {
        return new Notice(false, "", title, content);
    }

    public NoticeDto.CreateResDto toCreateResDto() {
        return NoticeDto.CreateResDto.builder().id(getId()).build();
    }
}