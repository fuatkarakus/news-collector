<template>
  <div class="about">
    <Navbar />
    <h1>First Vue project.</h1>
  </div>
</template>

<script>
  import Navbar from '@/components/Navbar.vue';

  export default {
    name: "About",
    components: {
      Navbar
    }
  };
</script>
