package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CreatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.dto.PartiallyUpdateCustomerRequest;
import com.polovyi.ivan.dto.PartiallyUpdatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.PurchaseTransactionResponse;
import com.polovyi.ivan.dto.UpdateCustomerRequest;
import com.polovyi.ivan.dto.UpdatePurchaseTransactionRequest;
import com.polovyi.ivan.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public record CustomerGraphQLController(CustomerService customerService) {

    @QueryMapping(name = "customers")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @MutationMapping
    public CustomerResponse createCustomer(@Argument  @Valid CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    @MutationMapping
    public CustomerResponse updateCustomer(
            @Argument @NotNull String customerId,
            @Argument @Valid @NotNull UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateCustomer(customerId, updateCustomerRequest);
    }

    @MutationMapping
    public CustomerResponse partiallyUpdateCustomer(
            @Argument @NotNull String customerId,
            @Argument @Valid @NotNull PartiallyUpdateCustomerRequest partiallyUpdateCustomerRequest) {
        return  customerService.partiallyUpdateCustomer(customerId, partiallyUpdateCustomerRequest);
    }

    @MutationMapping
    public String deleteCustomer(
            @Argument @NotNull String customerId) {
        customerService.deleteCustomer(customerId);
        return customerId;
    }

    @MutationMapping
    public PurchaseTransactionResponse createPurchaseTransaction(
            @Argument @NotNull String customerId,
            @Argument @Valid @NotNull CreatePurchaseTransactionRequest purchaseTransactionRequest) {
        return customerService.createPurchaseTransaction(customerId, purchaseTransactionRequest);
    }

    @MutationMapping
    public PurchaseTransactionResponse updatePurchaseTransaction(
            @Argument @NotNull String customerId,
            @Argument @NotNull String purchaseTransactionId,
            @Argument @Valid @NotNull UpdatePurchaseTransactionRequest updatePurchaseTransactionRequest) {
        return customerService.updatePurchaseTransactionsById(customerId, purchaseTransactionId,
                updatePurchaseTransactionRequest);
    }

    @MutationMapping
    public PurchaseTransactionResponse partiallyUpdatePurchaseTransaction(
            @Argument @NotNull String customerId,
            @Argument @NotNull String purchaseTransactionId,
            @Argument @Valid @NotNull PartiallyUpdatePurchaseTransactionRequest partiallyUpdatePurchaseTransactionRequest) {
        return  customerService.partiallyUpdatePurchaseTransaction(customerId, purchaseTransactionId,
                partiallyUpdatePurchaseTransactionRequest);

    }

    @MutationMapping
    public String deletePurchaseTransaction(
            @Argument @NotNull String customerId,
            @Argument @NotNull String purchaseTransactionId) {
        customerService.deletePurchaseTransactionsById(customerId, purchaseTransactionId);
        return purchaseTransactionId;
    }

}
