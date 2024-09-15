package br.com.book.api.domain.enums;

public enum OrderEnum {

    STANDARD {
        @Override
        public double getCost(double weight) {
            return weight * 5.0;
        }
    },
    EXPRESS {
        @Override
        public double getCost(double weight) {
            return weight * 10.0;
        }
    },
    SAME_DAY {
        @Override
        public double getCost(double weight) {
            return weight * 20.0;
        }
    },
    INTERNATIONAL {
        @Override
        public double getCost(double weight) {
            return weight * 50.0;
        }
    },
    OVERNIGHT {
        @Override
        public double getCost(double weight) {
            return weight * 30.0;
        }
    };

    public abstract double getCost(double weight);
}
