package com.dtxytech.boot.event;

import com.dtxytech.boot.entity.event.Equepment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.event
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:49
 * @DESC : 单个实体类 主要用来测试 事件  @DomainEvents
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquepmentSaveEvent {
    private Equepment equepment;

    public void doSomeThing(){
        log.debug("事件监听生效 EquepmentSaveEvent：获取equepment,{}",equepment);
    }





}
