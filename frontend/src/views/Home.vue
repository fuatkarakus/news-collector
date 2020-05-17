<template>
  <div id="home">
    <Navbar />
    <News />
  </div>
</template>

<script>
  import News from '@/components/News.vue';
  import Navbar from '@/components/Navbar.vue';
  export default {
    name: 'Home',
    components: {
      Navbar,
      News
    }
  };
</script>

<style>

</style>

