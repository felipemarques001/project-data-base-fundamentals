package com.FelipeMarques.salesController.mappers;

import com.FelipeMarques.salesController.dtos.purchaseDtos.DetailedPurchaseResponse;
import com.FelipeMarques.salesController.dtos.purchaseDtos.LightDetailedPurchaseResponse;
import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseRequest;
import com.FelipeMarques.salesController.dtos.purchaseDtos.PurchaseResponse;
import com.FelipeMarques.salesController.models.PurchaseModel;

import java.util.ArrayList;
import java.util.List;

public class PurchaseMapper {

    public static PurchaseModel toPurchaseModel(PurchaseRequest dto) {
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setWeight(dto.weight());
        return purchaseModel;
    }

    public static PurchaseResponse toPurchaseResponse(PurchaseModel purchaseModel) {
        return new PurchaseResponse(purchaseModel.getId(),
                purchaseModel.getProduct().getId(),
                purchaseModel.getWeight(),
                purchaseModel.getSale().getId());
    }

    public static DetailedPurchaseResponse toDetailedPurchaseResponse(PurchaseModel purchaseModel) {
        return new DetailedPurchaseResponse(purchaseModel.getId(),
                purchaseModel.getProduct(),
                purchaseModel.getWeight(),
                purchaseModel.getSale().getId());
    }

    private static LightDetailedPurchaseResponse toLightDetailedPurchaseResponse(PurchaseModel purchaseModel) {
        return new LightDetailedPurchaseResponse(purchaseModel.getId(),
                purchaseModel.getProduct(),
                purchaseModel.getWeight());
    }

    public static List<PurchaseResponse> toPurchaseResponseList(List<PurchaseModel> purchaseModelList) {
        List<PurchaseResponse> purchaseResponseList = new ArrayList<>();
        purchaseModelList.forEach( purchaseModel -> {
            purchaseResponseList.add(toPurchaseResponse(purchaseModel));
        });
        return purchaseResponseList;
    }

    public static List<DetailedPurchaseResponse> toDetailedPurchaseResponseList(List<PurchaseModel> purchaseModelList) {
        List<DetailedPurchaseResponse> detailedPurchaseResponseList = new ArrayList<>();
        purchaseModelList.forEach(purchaseModel -> {
            detailedPurchaseResponseList.add(toDetailedPurchaseResponse(purchaseModel));
        });
        return detailedPurchaseResponseList;
    }

    public static List<LightDetailedPurchaseResponse> toLightDetailedPurchaseResponseList(List<PurchaseModel> purchaseModelList) {
        List<LightDetailedPurchaseResponse> lightDetailedPurchaseResponseList = new ArrayList<>();
        purchaseModelList.forEach(purchaseModel -> {
            lightDetailedPurchaseResponseList.add(toLightDetailedPurchaseResponse(purchaseModel));
        });
        return lightDetailedPurchaseResponseList;
    }
}
