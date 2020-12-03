<template>
  <boards-list />
</template>

<script>
import BoardsList from '@/components/BoardsList';

export default {
  components: {   //List of Vue components used in the template
    BoardsList    //in the template we use kabob-case to reference the component <board-list>
  }
};
</script>
