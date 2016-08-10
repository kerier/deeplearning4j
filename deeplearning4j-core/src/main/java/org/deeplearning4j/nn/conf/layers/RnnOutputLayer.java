package org.deeplearning4j.nn.conf.layers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

@Data @NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RnnOutputLayer extends BaseOutputLayer {

	private RnnOutputLayer(Builder builder){
		super(builder);
	}

    @Override
    public InputType getOutputType(InputType inputType) {
        if(inputType == null || inputType.getType() != InputType.Type.RNN){
            throw new IllegalStateException("Invalid input type: Expected RNN input, got " + inputType);
        }
        return InputType.recurrent(nOut);
    }

    public static class Builder extends BaseOutputLayer.Builder<Builder> {

        public Builder(){
            this.lossFunction = LossFunction.MCXENT;
        }

    	public Builder(LossFunction lossFunction) {
            this.lossFunction = lossFunction;
        }
        
        @Override
        @SuppressWarnings("unchecked")
        public RnnOutputLayer build() {
            return new RnnOutputLayer(this);
        }
    }
}
