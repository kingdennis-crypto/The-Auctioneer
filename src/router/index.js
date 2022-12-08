import { createRouter, createWebHashHistory } from 'vue-router';

// Components
import WelcomeVue from '@/components/Welcome.vue';

// Overview Pages
import Overview33Vue from '@/components/offers/Overview33.vue';
import Overview32Vue from '@/components/offers/Overview32.vue';
import Overview31Vue from '@/components/offers/Overview31.vue';
import Overview34Vue from '@/components/offers/Overview34.vue';
import Overview37Vue from "@/components/offers/Overview37.vue";
import Overview37cached from "@/components/offers/Overview37cached";

// Detail Pages
import Detail32 from "@/components/offers/Detail32.vue";
import Detail34 from "@/components/offers/Detail34.vue";
import Detail37 from "@/components/offers/Detail37";
import Detail37cached from "@/components/offers/Detail37cached";

// Miscellaneous
import UnkownRoute from '@/components/UnknownRoute.vue';

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
    ]},
  { path: '/:pathMatch(.*)', component: UnkownRoute },

];

export const router = createRouter({
  history: createWebHashHistory(),
  routes
})