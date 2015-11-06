package sample;

/**
 * Created by srinath on 10/12/2015.
 */
public class CompanyProfit {
    private int id;
    private float companyBuyPrice;
    private float companySellPrice;
    private float profit;

    CompanyProfit(){
        this.companyBuyPrice = 0;
        this.companySellPrice = 0;
        this.profit = 0;
        this.id = 1;
    }

    CompanyProfit(int id, float companyBuyPrice, float companySellPrice, float profit ){
        this.companyBuyPrice = companyBuyPrice;
        this.companySellPrice = companySellPrice;
        this.profit = profit;
        this.id = id;
    }
    CompanyProfit(int id, float companyBuyPrice, float companySellPrice ){
        this.companyBuyPrice = companyBuyPrice;
        this.companySellPrice = companySellPrice;
        this.profit = 0;
        this.id = id;
    }

    public int getId(){return this.id;}
    public void setCompanyBuyPrice(float n){this.companyBuyPrice = n;}
    public float getCompanyBuyPrice(){return this.companyBuyPrice;}
    public void setProfit(float n){this.profit = n;}
    public float getProfit(){return this.profit;}
    public void setCompanySellPrice(float n){this.companySellPrice = n;}
    public float getCompanySellPrice(){return this.companySellPrice;}
    public void addCompanyProfit(float n){this.profit += n;}
}
