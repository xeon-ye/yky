package com.deloitte.services.fssc.business.borrow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.borrow.param.BorrowMoneyLineQueryForm;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.deloitte.services.fssc.business.borrow.mapper.BorrowMoneyLineMapper;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :  BorrowMoneyLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BorrowMoneyLineServiceImpl extends ServiceImpl<BorrowMoneyLineMapper, BorrowMoneyLine> implements IBorrowMoneyLineService {


    @Autowired
    private BorrowMoneyLineMapper borrowMoneyLineMapper;

    @Override
    public IPage<BorrowMoneyLine> selectPage(BorrowMoneyLineQueryForm queryForm ) {
        QueryWrapper<BorrowMoneyLine> queryWrapper = new QueryWrapper <BorrowMoneyLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return borrowMoneyLineMapper.selectPage(new Page<BorrowMoneyLine>(
                queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BorrowMoneyLine> selectList(BorrowMoneyLineQueryForm queryForm) {
        QueryWrapper<BorrowMoneyLine> queryWrapper = new QueryWrapper <BorrowMoneyLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return borrowMoneyLineMapper.selectList(queryWrapper);
    }

    @Override
    public List<BorrowMoneyLine> selectList(Long borrowId) {
        QueryWrapper<BorrowMoneyLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID,borrowId);
        return this.list(queryWrapper);
    }

    @Override
    public BorrowMoneyLine getByDocumentAndLineNum(Long borrowId, Long lineNum) {
        QueryWrapper<BorrowMoneyLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID,borrowId);
        queryWrapper.eq(BorrowMoneyLine.LINE_NUMBER,lineNum);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean existsByExpenseSubTypeIds(List<Long> ExpenseSubTypeList) {
        return borrowMoneyLineMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }


}

