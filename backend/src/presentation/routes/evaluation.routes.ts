import { Router } from 'express';
import { evaluationController } from '@/container';

export const evaluationRouter = Router();

evaluationRouter.post('/', evaluationController.submitEvaluation);
evaluationRouter.get('/student/:studentId', evaluationController.getStudentEvaluations);
