package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.controller.lazydatamodel.impl.LazyJmsDTODataModel;
import org.primefaces.model.LazyDataModel;
import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.repository.JmsService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class JmsController {

    @Inject
    private JmsService jmsService;

    private String quickSearchPhrase;

    private LazyJmsDTODataModel lazyDataModel;

    private List<JmsDTO> jmsDTOList;

    public List<JmsDTO> getJmsDTOList() {
        return jmsDTOList;
    }

    @PostConstruct
    public void init() {
        quickSearchPhrase = "";
        jmsDTOList = new ArrayList<JmsDTO>();
        jmsDTOList = jmsService.getAll();
        lazyDataModel = new LazyJmsDTODataModel(jmsDTOList);
    }

    public void refreshJms() {
        jmsDTOList = new ArrayList<JmsDTO>();
        jmsDTOList = jmsService.getAll();
        lazyDataModel = new LazyJmsDTODataModel(jmsDTOList);
        if (this.getQuickSearchPhrase() != null) {
            this.setQuickSearchPhrase("");
        }
    }

    public void deleteCheckedJmses() {
        List<Long> idList = new ArrayList<Long>();
        List<JmsDTO> removeList = new ArrayList<JmsDTO>();
        if (jmsDTOList != null) {
            for (JmsDTO item : jmsDTOList) {
                if (item.getFlag()) {
                    idList.add(item.getId());
                    removeList.add(item);
                }
            }

            for (JmsDTO item : removeList) {
                jmsDTOList.remove(item);
            }

            jmsService.deleteIdList(idList);
            idList.clear();
            removeList.clear();
        }
    }

    public void quickSearch(){
        if (this.getQuickSearchPhrase() == null || "".equals(getQuickSearchPhrase()) ) {
            this.refreshJms();
        } else {
            jmsDTOList = jmsService.quickSearch(this.getQuickSearchPhrase());
            lazyDataModel = new LazyJmsDTODataModel(jmsDTOList);
        }
    }

    public String getQuickSearchPhrase() {
        return quickSearchPhrase;
    }

    public void setQuickSearchPhrase(String quickSearchPhrase) {
        this.quickSearchPhrase = quickSearchPhrase;
    }

    public LazyDataModel getLazyDataModel() {
        return lazyDataModel;
    }
}
