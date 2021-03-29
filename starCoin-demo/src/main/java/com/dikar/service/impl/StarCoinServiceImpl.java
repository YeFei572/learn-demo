package com.dikar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.entity.StarCoin;
import com.dikar.mapper.StarCoinMapper;
import com.dikar.service.IStarCoinService;
import org.springframework.stereotype.Service;

@Service
public class StarCoinServiceImpl extends ServiceImpl<StarCoinMapper, StarCoin> implements IStarCoinService {
}
