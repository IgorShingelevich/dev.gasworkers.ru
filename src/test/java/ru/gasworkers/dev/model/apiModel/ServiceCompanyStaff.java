package ru.gasworkers.dev.model.apiModel;

public class ServiceCompanyStaff {
    public final SSSR SSSR = new SSSR();
    public final GASTECH GASTECH = new GASTECH();
    public final EGIDA EGIDA = new EGIDA();

    public class SSSR {
        public final int ID = 39;

        public final Master SSSRMaster1 = new Master(487);
        public final Master SSSRMaster2 = new Master(488);

        public final Dispatcher SSSRDispatcher1 = new Dispatcher(462);
        public final Dispatcher SSSRDispatcher2 = new Dispatcher(496);
    }

    public class GASTECH {
        public final int ID = 9;

        public final Master GASTECHMasterSidorov = new Master(229);
        public final Master GASTECHMasterLobanov = new Master(260);

        public final Dispatcher GASTECHDispatcherFedorova = new Dispatcher(304);
        public final Dispatcher GASTECHDispatcherDanilova = new Dispatcher(68);
    }

    public class EGIDA {
        public final int ID = 34;

        public final Master EGIDAMaster1 = new Master(437);
        public final Master EGIDAMaster2 = new Master(438);

        public final Dispatcher EGIDADispatcher1 = new Dispatcher(441);
//        public final Dispatcher EGIDADispatcher2 = new Dispatcher(69);
    }

    public class Master {
        private final int id;

        public Master(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public class Dispatcher {
        private final int id;

        public Dispatcher(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
