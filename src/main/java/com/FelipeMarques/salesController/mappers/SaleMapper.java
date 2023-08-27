package com.FelipeMarques.salesController.mappers;

import com.FelipeMarques.salesController.dtos.saleDtos.DetailedSaleResponse;
import com.FelipeMarques.salesController.dtos.saleDtos.LightDetailedSaleResponse;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleRequest;
import com.FelipeMarques.salesController.dtos.saleDtos.SaleResponse;
import com.FelipeMarques.salesController.models.SaleModel;

import java.util.ArrayList;
import java.util.List;

public class SaleMapper {

    public static SaleModel toSaleModel(SaleRequest dto) {
        SaleModel saleModel = new SaleModel();
        saleModel.setSaleDate(dto.saleDate());
        saleModel.setTotalValue(dto.totalValue());
        saleModel.setPaymentStatus(dto.paymentStatus());
        return saleModel;
    }

    public static SaleResponse toSaleResponse(SaleModel saleModel) {
        return new SaleResponse(saleModel.getId(),
                saleModel.getSaleDate(),
                saleModel.getTotalValue(),
                saleModel.getPaymentStatus(),
                saleModel.getCustomer().getId());
    }

    public static DetailedSaleResponse toDetailedSaleResponse(SaleModel saleModel) {
        return new DetailedSaleResponse(saleModel.getId(),
                saleModel.getSaleDate(),
                saleModel.getTotalValue(),
                saleModel.getPaymentStatus(),
                saleModel.getCustomer().getId(),
                PurchaseMapper.toLightDetailedPurchaseResponseList(saleModel.getPurchases()));
    }

    private static LightDetailedSaleResponse toLightDetailedSaleResponse(SaleModel saleModel) {
        return new LightDetailedSaleResponse(saleModel.getId(),
                saleModel.getSaleDate(),
                saleModel.getTotalValue(),
                saleModel.getPaymentStatus(),
                PurchaseMapper.toLightDetailedPurchaseResponseList(saleModel.getPurchases()));
    }

    public static List<LightDetailedSaleResponse> toLightDetailedSaleResponseList(List<SaleModel> saleModelList) {
        List<LightDetailedSaleResponse> lightDetailedSaleResponseList = new ArrayList<>();
        saleModelList.forEach(saleModel -> {
            lightDetailedSaleResponseList.add(toLightDetailedSaleResponse(saleModel));
        });
        return lightDetailedSaleResponseList;
    }

    public static List<SaleResponse> toSaleResponseList(List<SaleModel> saleModelList) {
        List<SaleResponse> saleResponseList = new ArrayList<>();
        saleModelList.forEach(saleModel -> {
            saleResponseList.add(toSaleResponse(saleModel));
        });
        return saleResponseList;
    }

    public static List<DetailedSaleResponse> toDetailedSaleResponseList(List<SaleModel> saleModelList) {
        List<DetailedSaleResponse> detailedSaleResponseList = new ArrayList<>();
        saleModelList.forEach(saleModel -> {
            detailedSaleResponseList.add(toDetailedSaleResponse(saleModel));
        });
        return detailedSaleResponseList;
    }
}
