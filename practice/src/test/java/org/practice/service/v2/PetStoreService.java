package org.practice.service.v2;

import lombok.*;
import org.practice.dao.v2.AccountDao;
import org.practice.dao.v2.ItemDao;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/22 10:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;
    private Integer version;
}
