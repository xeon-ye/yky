package com.deloitte.services.fssc.business.ppc.biz;

public interface ProjectConfirmationBiz<T,Z,K> {

    T saveOrUpdate(Z z);


    void deleteById(Long documentId);


    K getById(Long documentId);

}

