//package ru.gasworkers.dev.tests.api.story.repair;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//
//@RequiredArgsConstructor
//@Getter
//public enum StateTransitionRepair {
//
//    PUBLISHED {
//        @Override
//        public StateTransitionRepair nextAfterOffer() {
//            return HAS_OFFER;
//        }
//    },
//
//    HAS_OFFER {
//        @Override
//        public StateTransitionRepair nextAfterPayment() {
//            return BEFORE_PAYMENT;
//        }
//
//        @Override
//        public StateTransitionRepair nextAfterDateSelect() {
//            return DATE_SELECTING;
//        }
//    },
//
//    BEFORE_PAYMENT {
//        @Override
//        public StateTransitionRepair nextAfterDateSelect() {
//            return DATE_SELECTING;
//        }
//    },
//
//    DATE_SELECTING {
//        @Override
//        public StateTransitionRepair nextAfterDateApprove() {
//            return WAITING_MASTER;
//        }
//    },
//
//    WAITING_MASTER {
//        @Override
//        public StateTransitionRepair nextAfterMasterStart() {
//            return MASTER_START_WORKING;
//        }
//    },
//
//    MASTER_START_WORKING {
//        @Override
//        public StateTransitionRepair nextAfterMaterialValues() {
//            return MATERIAL_INVOICE_ISSUED;
//        }
//    },
//
//    MATERIAL_INVOICE_ISSUED {
//        @Override
//        public StateTransitionRepair nextAfterMaterialValuesPaid() {
//            return MATERIAL_INVOICE_PAID;
//        }
//    },
//
//    MATERIAL_INVOICE_PAID {
//        @Override
//        public StateTransitionRepair nextAfterActionsDone() {
//            return ACTIONS_INVOICE_ISSUED;
//        }
//    },
//
//    ACTIONS_INVOICE_ISSUED {
//        @Override
//        public StateTransitionRepair nextAfterActionsPaid() {
//            return ACTIONS_INVOICE_PAID;
//        }
//    },
//
//    ACTIONS_INVOICE_PAID {
//        @Override
//        public StateTransitionRepair nextAfterMasterSign() {
//            return ACT_SIGNED_MASTER;
//        }
//    },
//
//    ACT_SIGNED_MASTER {
//        @Override
//        public StateTransitionRepair nextAfterClientSign() {
//            return ACT_SIGNED_CLIENT;
//        }
//    },
//
//    ACT_SIGNED_CLIENT;
//
//    private final StateTransitionRepair nextTransition;
//    @Setter
//    private Date transitionDate;
//
//    public abstract StateTransitionRepair nextAfterOffer();
//
//    public abstract StateTransitionRepair nextAfterPayment();
//
//    public abstract StateTransitionRepair nextAfterDateSelect();
//
//    public abstract StateTransitionRepair nextAfterDateApprove();
//
//    public abstract StateTransitionRepair nextAfterMasterStart();
//
//    public abstract StateTransitionRepair nextAfterMaterialValues();
//
//    public abstract StateTransitionRepair nextAfterMaterialValuesPaid();
//
//    public abstract StateTransitionRepair nextAfterActionsDone();
//
//    public abstract StateTransitionRepair nextAfterActionsPaid();
//
//    public abstract StateTransitionRepair nextAfterMasterSign();
//
//    public abstract StateTransitionRepair nextAfterClientSign();
//
//}