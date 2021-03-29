package com.dikar.components;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dikar.constants.Constants;
import com.dikar.entity.StarCoin;
import com.dikar.service.IStarCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StarCoinHandler {

    @Autowired
    private IStarCoinService starCoinService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void handlerStarCoin() throws InterruptedException {
        for (int i = 1; i < 3; i++) {
            String url = Constants.STAR_COIN_USER_LIST.replace("${page}", String.valueOf(i));
            String resp = HttpUtil.get(url);
            JSONObject jsonObject = JSONUtil.parseObj(resp);
            if (jsonObject.getInt("code") == 200) {
                JSONObject data = (JSONObject) jsonObject.get("data");
                JSONObject object = (JSONObject) data.get("hits");
                JSONArray array = (JSONArray) object.get("hits");
                List<StarCoin> starCoinList = array.stream().map(item -> {
                    JSONObject source = (JSONObject) ((JSONObject) item).get("_source");
                    JSONObject metadata = (JSONObject) source.get("metadata");
                    return StarCoin.builder()
                            .createTime(LocalDateTimeUtil.ofUTC(metadata.getLong("timestamp")))
                            .number(metadata.getStr("number"))
                            .author(metadata.getStr("author"))
                            .id(IdUtil.getSnowflake(1, 1).nextId())
                            .build();
                }).collect(Collectors.toList());
                // 排除已存在的block
                List<StarCoin> exist = starCoinService.list(Wrappers.<StarCoin>lambdaQuery().in(StarCoin::getNumber, starCoinList.stream().map(StarCoin::getNumber).collect(Collectors.toList())));
                if (exist.size() > 0) {
                    Iterator<StarCoin> it = starCoinList.iterator();
                    while (it.hasNext()) {
                        StarCoin starCoin = it.next();
                        if (exist.stream().map(StarCoin::getNumber).collect(Collectors.toList()).contains(starCoin.getNumber())) {
                            it.remove();
                        }
                    }
                }
                System.out.println(starCoinList.size());
                starCoinService.saveBatch(starCoinList);
                Thread.sleep(2000);
            }
        }

    }
}
