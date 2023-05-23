<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<style scoped>
.border{
  background-color: rgb(248, 248, 240);
  height: 600px;
  width: 450px;
  margin: auto;
  border: 1px solid rgba(0, 0, 0, 0);
  border-radius: 5px;
  position: absolute;
  top:20px;
  left:50%;
  transform: translate(-50%);
  max-height: auto;
}

.footer{
  height:auto;
  font-weight: 600;
  font-size: 110%;
  text-align: center;
  position: absolute;
  bottom: 0;
  left:50%;
  transform: translate(-50%);
}

.footer-heart{
  background: url("./assets/heart.svg") no-repeat;
  background-size: 20px 20px;
  background-position: center center;
  height: 20px;
  width: 20px;
  display: inline-block;
}

@media (max-width: 720px) {
  .border{
    width: 80%;
  }
}
</style>
<template>
  <main class="border">
        <RouterView />
  </main>
  <footer class="footer">
    Theme contact-management front By Moe_Nya With <i class="footer-heart"></i><br>
    Power By Vue3.0
  </footer>
</template>