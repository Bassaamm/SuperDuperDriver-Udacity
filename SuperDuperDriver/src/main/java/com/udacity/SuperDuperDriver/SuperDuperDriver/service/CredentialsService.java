package com.udacity.SuperDuperDriver.SuperDuperDriver.service;

import com.udacity.SuperDuperDriver.SuperDuperDriver.mapper.CredentialsMapper;
import com.udacity.SuperDuperDriver.SuperDuperDriver.model.Credential;
import com.udacity.SuperDuperDriver.SuperDuperDriver.model.CredentialForm;
import com.udacity.SuperDuperDriver.SuperDuperDriver.model.NoteForm;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public void addCredentials(CredentialForm newCredential, Integer userId ) throws NoSuchAlgorithmException {
        String key=encryptionService.generateKey();

        Credential credential = new Credential(newCredential,key,userId);
        credentialsMapper.insertCredential(credential);
    }
    public List<Credential> getCredentials(Integer userId){
        return credentialsMapper.getCredentialsByUserId(userId);
    }
    public void deleteCredential(Integer credentialId){
        credentialsMapper.deleteCredential(credentialId);
    }
    public void updateCredential(CredentialForm newCre){
        credentialsMapper.update(newCre);
    }

}
