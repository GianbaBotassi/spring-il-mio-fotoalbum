import { createRouter, createWebHistory } from 'vue-router';

import List from './components/List.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'List',
            component: List
        }
    ]
});
export { router };