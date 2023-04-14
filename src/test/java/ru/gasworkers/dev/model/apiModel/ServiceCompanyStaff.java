package ru.gasworkers.dev.model.apiModel;

public class ServiceCompanyStaff {
    public final SSSR SSSR = new SSSR();
    public final GASTECH GASTECH = new GASTECH();

    public class SSSR {
        public final String ID = "39";

        public final Master SSSRMaster1 = new Master("487");
        public final Master SSSRMaster2 = new Master("488");

        public final Dispatcher SSSRDispatcher1 = new Dispatcher("462");
        public final Dispatcher SSSRDispatcher2 = new Dispatcher("496");
    }

    public class GASTECH {
        public final String ID = "9";

        public final Master GASTECHMasterSidorov = new Master("229");
        public final Master GASTECHMasterLobanov = new Master("260");

        public final Dispatcher GASTECHDispatcherFedorova = new Dispatcher("304");
        public final Dispatcher GASTECHDispatcherDanilova = new Dispatcher("68");
    }

    public class Master {
        private final String id;

        public Master(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public class Dispatcher {
        private final String id;

        public Dispatcher(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}


