package br.com.mbecker.drinkcount;

public enum DrinkCategory {
    CERVEJA(R.drawable.cerveja), VINHO(R.drawable.vinho), CHOOP(R.drawable.choop),
    CAIPIRINHA(R.drawable.caipirinha), DRINKS(R.drawable.drink), OUTROS(R.drawable.outros);

    private final int imageId;

    DrinkCategory(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }
}