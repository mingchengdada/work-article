package com.mc.redis.controller;

import com.mc.redis.configuration.bean.AcAssetTransferDetail;
import com.mc.redis.configuration.bean.AcBillDepositFlow;
import com.mc.redis.configuration.bean.AcPersonalBook;
import com.mc.redis.configuration.mapper.AcAssetTransferDetailMapper;
import com.mc.redis.configuration.mapper.AcBillDepositFlowMapper;
import com.mc.redis.configuration.mapper.AcPersonalBookMapper;
import com.mc.redis.utils.RedisClusterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.redis.controller
 * 类名称:     BatchInsertRedisController
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/7/23 16:39
 */
@RestController
public class BatchInsertRedisController {
    @Autowired
    private RedisClusterTemplate redisClusterTemplate;
    @Autowired
    private AcAssetTransferDetailMapper acAssetTransferDetailMapper;
    @Autowired
    private AcPersonalBookMapper acPersonalBookMapper;
    @Autowired
    private AcBillDepositFlowMapper acBillDepositFlowMapper;


    @GetMapping("/insert")
    public String insert() {
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "white.txt");
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String redisKey;
            while ((redisKey = fileReader.readLine()) != null) {
                redisClusterTemplate.set(redisKey, "1");
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

    }

    @GetMapping("/validate")
    public String validate() throws IOException {
        File file = new File(System.getProperty("user.dir") + File.separator + "test.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        File file1 = new File(System.getProperty("user.dir") + File.separator + "validate.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1));
        String redisKey;
        while ((redisKey = fileReader.readLine()) != null) {
            String[] s = redisKey.split(",");
            if(s!=null && s.length >0) {
                for(String m : s) {
                    String key = "GRAY_WHITE_LIST_"+m;
                    String value = redisClusterTemplate.get(key);
                    if(value !=null && "1".equals(value)) {
                        bufferedWriter.write(key+":"+value+"\n");
                    } else {
                        bufferedWriter.write(key+":"+"null"+"\n");
                    }
                }
            }
        }
        return "success";
    }

    @GetMapping("/assertError")
    public String test() throws IOException {
        /* 全量查出迁移结果 */
        File file = new File(System.getProperty("user.dir") + File.separator + "errorAssert.txt");
        List<AcAssetTransferDetail> acAssetTransferDetails =  acAssetTransferDetailMapper.selectAll();
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
        for(AcAssetTransferDetail acAssetTransferDetail : acAssetTransferDetails) {
            String oldUserId = "";
            String newUserId = "";
            List<AcPersonalBook> oldBooks;
            List<AcPersonalBook> newBooks;
            List<AcBillDepositFlow> acBillDepositFlowNew = null;
            List<String> oldBookIds = new ArrayList<>();
            StringBuilder stringBuilder;
            oldUserId = acAssetTransferDetail.getSourceUserId();
            newUserId = acAssetTransferDetail.getTargetUserId();
            //状态为0的迁移过后的账本
            oldBooks = acPersonalBookMapper.selectAcctBookByUserId(oldUserId);
            if(null != oldBooks && oldBooks.size() > 0) {
                oldBooks.forEach(book -> {
                    oldBookIds.add(book.getAcctBookId());
                });
            } else {
                continue;
            }

            int count = 0;
            String oldUserId1 = oldUserId.startsWith("013U") ? oldUserId
                    .substring(4,oldUserId.length()) : "013U"+oldUserId;
//            for(String oldBookId : oldBookIds) {
                acBillDepositFlowNew = acBillDepositFlowMapper.selectDepositByUserIdAndBookId(newUserId,oldUserId,oldUserId1);
                if(null != acBillDepositFlowNew && acBillDepositFlowNew.size() > 0) {
                    count = count + acBillDepositFlowNew.size();
                }
//            }
            if(count != oldBooks.size()) {
                stringBuilder = new StringBuilder(oldUserId).append("|")
                        .append(newUserId).append("|").append(acAssetTransferDetail.getTaskId()).append("|")
                        .append(oldBooks.size()).append("|").append(acBillDepositFlowNew.size()).append("\n");
                fileWriter.write(stringBuilder.toString());
            }

        }
        fileWriter.close();
        return "success";
    }
}
