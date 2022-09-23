import { createRouter, createWebHashHistory } from 'vue-router';

// Components
import WelcomeVue from '@/components/Welcome.vue';
import Overview32Vue from '@/components/offers/Overview32.vue';
import Overview31Vue from '@/components/offers/Overview31.vue';
import UnkownRoute from '@/components/UnknownRoute.vue';

const routes = [
  { path: '/', component: WelcomeVue },
  { path: '/offers/overview31', component: Overview31Vue },
  { path: '/offers/overview32', component: Overview32Vue },
  { path: '/:pathMatch(.*)', component: UnkownRoute }
];

export const router = createRouter({
  history: createWebHashHistory(),
  routes
})