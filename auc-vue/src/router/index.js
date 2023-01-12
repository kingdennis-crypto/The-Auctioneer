import { createRouter, createWebHashHistory } from 'vue-router';

// Components
import WelcomeVue from '@/components/Welcome.vue';

// Overview Pages
import Overview33Vue from '@/components/offers/33/Overview33.vue';
import Overview32Vue from '@/components/offers/32/Overview32.vue';
import Overview31Vue from '@/components/offers/31/Overview31.vue';
import Overview34Vue from '@/components/offers/34/Overview34.vue';
import Overview37Vue from "@/components/offers/37/Overview37.vue";
import Overview37cached from "@/components/offers/37/Overview37cached";

// Detail Pages
import Detail32 from "@/components/offers/32/Detail32.vue";
import Detail34 from "@/components/offers/34/Detail34.vue";
import Detail37 from "@/components/offers/37/Detail37";
import Detail37cached from "@/components/offers/37/Detail37cached";

// Miscellaneous
import UnkownRoute from '@/components/UnknownRoute.vue';
import SignIn from '@/components/SignIn.vue';
import RequestErrorVue from '@/components/RequestError.vue';

const routes = [
  { path: '/', component: WelcomeVue },
  { path: '/offers/overview31', component: Overview31Vue },
  { path: '/offers/overview32', component: Overview32Vue },
  { path: '/offers/overview33', component: Overview33Vue, children: [
      { path: ":id", component: Detail32}
    ]
  },
  { path: '/offers/overview34', component: Overview34Vue, children: [
      { path: ":id", component: Detail34}
    ]},
  { path: '/offers/overview37', component: Overview37Vue, children: [
      { path: ':id', component: Detail37 }
    ]},
  { path: '/offers/overview37cached', component: Overview37cached, children: [
      { path: ':id', component: Detail37cached }
  ]
  },
  { path: '/sign-in', name: 'SignIn', component: SignIn },
  { path: '/sign-out', name: 'SignOut', redirect: '/sign-in?signOut=true' },
  { path: '/error', component: RequestErrorVue, props: true, name: 'ERROR' },
  { path: '/:pathMatch(.*)', component: UnkownRoute },
];

export const router = createRouter({
  history: createWebHashHistory(),
  routes
})