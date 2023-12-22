package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dao.ContactDao;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
import com.campusdual.appmazing.service.ContactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Mock
    IContactService iContactService;

    @InjectMocks
    ContactController contactController;

    Contact contact1 = new Contact();
    Contact contact2 = new Contact();


    @BeforeEach
    void init() {

        this.contact1.setId(1);
        this.contact1.setName("One");
        this.contact1.setSurname("Surname1One");
        this.contact1.setLastName("LastName2One");
        this.contact1.setTelephone("666555444");
        this.contact1.setEmail("contact-one@gmail.com");

        this.contact2.setId(2);
        this.contact2.setName("Two");
        this.contact2.setSurname("SurnameTwo");
        this.contact2.setLastName("LastNameTwo");
        this.contact2.setTelephone("666999888");
        this.contact2.setEmail("contact-two@gmail.com");
    }

    @Test
    void queryContactTest(){

        ContactDTO contactDTO = ContactMapper.INSTANCE.toDTO(this.contact1);
        ContactDTO expectedContactDTO = ContactMapper.INSTANCE.toDTO(this.contact1);
        //Mockito.when(IContactService.getReferenceById(Mockito.anyInt())).thenReturn(this.contact1);
        Mockito.when(iContactService.queryContact(Mockito.any())).thenReturn(expectedContactDTO);

        ContactDTO result = contactController.queryContact(contactDTO);

        Assertions.assertEquals(expectedContactDTO, result);
    }
    @Test
    void queryAllContactsTest() {
        List<ContactDTO> expectedContactList = new ArrayList<>();
        expectedContactList.add(ContactMapper.INSTANCE.toDTO(this.contact1));
        expectedContactList.add(ContactMapper.INSTANCE.toDTO(this.contact2));

        Mockito.when(iContactService.queryAllContacts()).thenReturn(expectedContactList);

        List<ContactDTO> result = contactController.queryAllContacts();

        Assertions.assertEquals(expectedContactList, result);
    }
    @Test
    void addContactTest(){
      Mockito.when(iContactService.insertContact(Mockito.any())).thenReturn(this.contact1.getId());
      int result = contactController.addContact(ContactMapper.INSTANCE.toDTO(this.contact1));
      Assertions.assertEquals(this.contact1.getId(),result);

    }
    @Test
    void updateContactTest(){

        Mockito.when(iContactService.updateContact(Mockito.any())).thenReturn(this.contact1.getId());
        int result = contactController.updateContact(ContactMapper.INSTANCE.toDTO(this.contact1));
        Assertions.assertEquals(this.contact1.getId(),result);

    }
    @Test
    void deleteContact(){

        Mockito.when(iContactService.deleteContact(Mockito.any())).thenReturn(this.contact1.getId());
        int result = contactController.deleteContact(ContactMapper.INSTANCE.toDTO(this.contact1));
        Assertions.assertEquals(this.contact1.getId(),result);

    }

    @Test
    void testContactControllerTest(){

        String result = contactController.testContactController();

        Assertions.assertEquals(result, "También va la de contactos¡¡¡");

    }



}


