package com.starritt.contract.controllers;

import com.starritt.contract.dto.Contract;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ContractController.CONTRACT_BASE_URI)
public class ContractController {
    public static final String CONTRACT_BASE_URI = "svc/v1/contracts";

    @RequestMapping(value = "1")
    public Contract getContractDylan()
    {
        Contract contract = new Contract();
        contract.setName("Dylan");
        contract.setId(1);
        return contract;
    }

    @RequestMapping(value = "2")
    public Contract getContractAustin()
    {
        Contract contract = new Contract();
        contract.setName("Austin");
        contract.setId(2);
        return contract;
    }


    @RequestMapping(value = "3")
    public Contract getContractNoah()
    {
        Contract contract = new Contract();
        contract.setName("Noah");
        contract.setId(3);
        return contract;
    }

    @RequestMapping(value = "4")
    public Contract getContractMeet()
    {
        Contract contract = new Contract();
        contract.setName("Meet");
        contract.setId(4);
        return contract;
    }
}
