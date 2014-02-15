package com.engagepoint.university.messaging.dao.specific.impl;

import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.util.Converter;
import com.engagepoint.university.messaging.util.EntityManagerUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SmsDAOImpl implements SmsDAO {
    @Override
    public SmsDTO getById(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Sms sms = EntityManagerUtil.getEntityManager().find(Sms.class, id);
        SmsDTO smsDTO = new Converter().convert(sms);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return smsDTO;
    }

    @Override
    public List<SmsDTO> getAll() {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Sms> smses = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Sms.GET_ALL_SMS, Sms.class).getResultList();
        List<SmsDTO> smsDTOs = new ArrayList<SmsDTO>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(new Converter().convert(smsIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return smsDTOs;
    }

    @Override
    public void save(SmsDTO smsDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Sms sms = new Converter().convert(smsDTO);
        if (!EntityManagerUtil.getEntityManager().contains(sms)) {
            EntityManagerUtil.getEntityManager().merge(sms);
        } else {
            EntityManagerUtil.getEntityManager().persist(sms);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(SmsDTO smsDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Sms sms = new Converter().convert(smsDTO);
        EntityManagerUtil.getEntityManager().merge(sms);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Sms sms = EntityManagerUtil.getEntityManager().find(Sms.class, id);
        if (sms != null) {
            EntityManagerUtil.getEntityManager().detach(sms);
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(SmsDTO smsDTO) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        Sms sms = new Converter().convert(smsDTO);
        EntityManagerUtil.getEntityManager().detach(sms);
        EntityManagerUtil.getEntityManager().getTransaction().commit();
    }

    @Override
    public List<SmsDTO> getSmsBySender(String sender) {
        EntityManagerUtil.getEntityManager().getTransaction().begin();
        List<Sms> smses = EntityManagerUtil.getEntityManager()
                .createNamedQuery(Sms.GET_ALL_BY_SENDER, Sms.class)
                .setParameter("sender", sender).getResultList();
        List<SmsDTO> smsDTOs = new ArrayList<SmsDTO>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(new Converter().convert(smsIterator.next()));
        }
        EntityManagerUtil.getEntityManager().getTransaction().commit();
        return smsDTOs;
    }

    @Override
    public void saveSmsDAO(SmsDTO smsDTO) {

    }
}