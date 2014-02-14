package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.entities.base.Base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = Attachment.GET_ALL_BY_ATTACHMENT_ID, query = "SELECT at FROM Attachment at WHERE at.idAttachment = :idAttachment"),
//        @NamedQuery(name = Attachment.GET_ALL_BY_MIME_TYPE, query = "SELECT at FROM Attachment at WHERE at.mimeType = :mimeType"),
        @NamedQuery(name = Attachment.GET_ALL_BY_NAME, query = "SELECT at FROM Attachment at WHERE at.name = :name"),
        @NamedQuery(name = Attachment.GET_ATTACHMENT_BY_MESSAGE, query = "SELECT at FROM Attachment at ")})

public class Attachment extends Base implements Serializable {

    private static final long serialVersionUID = 765348739781231295L;
    public static final String GET_ALL_BY_ATTACHMENT_ID = "Attachment.findByIdAttachment";
    public static final String GET_ALL_BY_MIME_TYPE = "Attachment.findByMimeType";
    public static final String GET_ALL_BY_NAME = "Attachment.findByName";
    public static final String GET_ATTACHMENT_BY_MESSAGE = "Attachment.getAttachmentByMessage";

    @Id
    @NotNull
    private Integer idAttachment;

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 45)
//    private String mimeType;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Basic(optional = false)
    @NotNull
    @Lob
    private String content;

    @ManyToMany
    private Collection<Email> emailCollection;

    public Attachment() {
    }

    public Attachment(AttachmentDTO attachmentDTO) {
//        this.mimeType = attachmentDTO.getMimeType();
        this.name = attachmentDTO.getName();
        this.content = attachmentDTO.getContent();
    }

    public Integer getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Integer idAttachment) {
        this.idAttachment = idAttachment;
    }

//    public String getMimeType() {
//        return mimeType;
//    }
//
//    public void setMimeType(String mimeType) {
//        this.mimeType = mimeType;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Collection<Email> getEmailCollection() {
        return emailCollection;
    }

    public void setEmailCollection(Collection<Email> emailCollection) {
        this.emailCollection = emailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttachment != null ? idAttachment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) object;
        if ((this.idAttachment == null && other.idAttachment != null) || (this.idAttachment != null && !this.idAttachment.equals(other.idAttachment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Attachment[ idAttachment=" + idAttachment + " ]";
    }

}
