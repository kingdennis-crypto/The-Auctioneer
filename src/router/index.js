import { createRouter, createWebHashHistory } from 'vue-router';

// Components
import WelcomeVue from '@/components/Welcome.vue';
import Overview33Vue from '@/components/offers/Overview33.vue';
import Overview32Vue from '@/components/offers/Overview32.vue';
import Overview31Vue from '@/components/offers/Overview31.vue';
import UnkownRoute from '@/components/UnknownRoute.vue';
import Detail32 from "@/components/offers/Detail32.vue";

const routes = [
  { path: '/', component: WelcomeVue },
  { path: '/offers/overview31', component: Overview31Vue },
  { path: '/offers/overview32', component: Overview32Vue },
  { path: '/offers/overview33', component: Overview33Vue, children: [
      { path: ":id", component: Detail32}
    ]},
  { path: '/:pathMatch(.*)', component: UnkownRoute },
];

export const router = createRouter({
  history: createWebHashHistory(),
  routes
})