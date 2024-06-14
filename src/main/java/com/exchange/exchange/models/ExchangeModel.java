package com.exchange.exchange.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExchangeModel {
    private Sender sender;
    private Recipient recipient;
    private Rate rate;
    private String fxTooltip;
    private List<Plan> plans;

    @JsonCreator
    public ExchangeModel(
            @JsonProperty("sender") Sender sender,
            @JsonProperty("recipient") Recipient recipient,
            @JsonProperty("rate") Rate rate,
            @JsonProperty("fxTooltip") String fxTooltip,
            @JsonProperty("plans") List<Plan> plans) {
        this.sender = sender;
        this.recipient = recipient;
        this.rate = rate;
        this.fxTooltip = fxTooltip;
        this.plans = plans;
    }

    public SmallerExchangeModel toSmallerExchangeModel() {
        return new SmallerExchangeModel(
                getSender().getCurrency(),
                getSender().getAmount(),
                getRecipient().getCurrency(),
                getRecipient().getAmount()
        );
    }
}

@Data
class Sender {
    private Long amount;
    private String currency;

    @JsonCreator
    public Sender(@JsonProperty("amount") Long amount, @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}

@Data
class Recipient {
    private Long amount;
    private String currency;

    @JsonCreator
    public Recipient(@JsonProperty("amount") Long amount, @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}

@Data
class Rate {
    private String from;
    private String to;
    private Double rate;
    private Long timestamp;

    @JsonCreator
    public Rate(@JsonProperty("from") String from, @JsonProperty("to") String to, @JsonProperty("rate") Double rate, @JsonProperty("timestamp") Long timestamp) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.timestamp = timestamp;
    }
}

@Data
class Plan {
    private String id;
    private String name;
    private Fees fees;
    private String tooltipLong;
    private String tooltipShort;

    @JsonCreator
    public Plan(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("fees") Fees fees, @JsonProperty("tooltipLong") String tooltipLong, @JsonProperty("tooltipShort") String tooltipShort) {
        this.id = id;
        this.name = name;
        this.fees = fees;
        this.tooltipLong = tooltipLong;
        this.tooltipShort = tooltipShort;
    }
}

@Data
class Fees {
    private Fx fx;
    private FxWeekend fxWeekend;
    private Total total;
    private Cost cost;

    @JsonCreator
    public Fees(@JsonProperty("fx") Fx fx, @JsonProperty("fxWeekend") FxWeekend fxWeekend, @JsonProperty("total") Total total, @JsonProperty("cost") Cost cost) {
        this.fx = fx;
        this.fxWeekend = fxWeekend;
        this.total = total;
        this.cost = cost;
    }
}

@Data
class Fx {
    private Long amount;
    private String currency;

    @JsonCreator
    public Fx(@JsonProperty("amount") Long amount, @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}

@Data
class FxWeekend {
    private Amount amount;

    @JsonCreator
    public FxWeekend(@JsonProperty("amount") Amount amount) {
        this.amount = amount;
    }
}

@Data
class Amount {
    private Amount2 amount;
    private String percentage;

    @JsonCreator
    public Amount(@JsonProperty("amount") Amount2 amount, @JsonProperty("percentage") String percentage) {
        this.amount = amount;
        this.percentage = percentage;
    }
}

@Data
class Amount2 {
    private Long amount;
    private String currency;

    @JsonCreator
    public Amount2(@JsonProperty("amount") Long amount, @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}

@Data
class Total {
    private Long amount;
    private String currency;

    @JsonCreator
    public Total(@JsonProperty("amount") Long amount, @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}

@Data
class Cost {
    private Long amount;
    private String currency;

    @JsonCreator
    public Cost(@JsonProperty("amount") Long amount, @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}



